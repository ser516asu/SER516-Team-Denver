import React, {useState} from 'react'
import { ToggleButton,
ToggleButtonGroup, 
Box } from '@mui/material'

const DeliveryOnTime = ({ milestone }) => {
    const [parameter, setParameter] = useState('')
    return (
        <Box>
            <ToggleButtonGroup
                exclusive
                sx={{ mt: 2 }}
                value={parameter}
                onChange={(event, newParameter) => setParameter(newParameter)}
                >
                <ToggleButton key="BV" value="BV">
                    Business Value
                </ToggleButton>
                <ToggleButton key="SP" value="SP">
                    Story Points
                </ToggleButton>
            </ToggleButtonGroup>
            { parameter }
        </Box>
    )
}

export default DeliveryOnTime