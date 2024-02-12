// ProjectDetails.js
import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import '../styles/ProjectDetails.css'
import { 
  CircularProgress, 
  ListItem,
  ListItemAvatar,
  Avatar,
  ListItemText,
  Stack,
  Divider } from '@mui/material';
import MetricsSection from '../components/Metrics';
import PersonIcon from '@mui/icons-material/Person';


const ProjectDetails = () => {
  const [isLoading, setIsLoading] = useState(true)
  const [project, setProject] = useState(null);
  const { projectId } = useParams();

  useEffect(() => {
    const fetchProjectDetails = async () => {
      try {
        const response = await fetch(`http://localhost:8080/api/projects/${projectId}`);
        if (response.ok) {
          const data = await response.json();
          console.log('Data', data);  
          setProject(data);
        } else {
          throw new Error('Failed to fetch project details');
        }
      } catch (error) {
        console.error('Error fetching project details:', error);
      }
    };

    fetchProjectDetails();
  }, [projectId]);

  if (!project) {
    return (
      <div style = {{position: 'absolute', 
      top: '50%', 
      left: '50%', 
      transform: 'translate(-50%, -50%)',}}>
          {isLoading && <CircularProgress />}
      </div>
    )
  }

  return (
    <div className="app">
      <header>
        <div className="title-container">
          <h1 className='project-title'>{project.projectName}</h1>
          <p className='project-subtitle'>
            {project.description}
          </p>
          <p className='project-subtitle'>
            Created At: {project.createdDate}
          </p>
          <p className='project-subtitle'>
            Owner: {project.owner}
          </p>
        </div>
      </header>
      <main>
        <div className="content-container">
          <div className="content">
          <h4>Members:</h4>          
            <Stack direction="row" spacing={1} divider={<Divider orientation="vertical" flexItem />}>
              {project.members.map((member) => (
                <ListItem key={member}>
                  <ListItemAvatar>
                    <Avatar>
                      <PersonIcon />
                    </Avatar>
                  </ListItemAvatar>
                  <ListItemText primary={member} />
                </ListItem>
              ))}
            </Stack>
            <h2 style={{marginBottom: '2em'}}>Metrics:</h2>
            <Divider />
            <MetricsSection project={project}/>
          </div>
        </div>
      </main>
    </div>
  );
};

export default ProjectDetails;
