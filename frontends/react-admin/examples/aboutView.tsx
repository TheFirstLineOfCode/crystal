import {useState} from 'react';
import Button from '@mui/material/Button';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import {fetchUtils} from 'react-admin'

export const AboutView = () => {
	const [showDialog, setShowDialog] = useState(false);
	const openDialog = () => {
		setShowDialog(true);
	};
	const closeDialog = () => {
		setShowDialog(false);
	};
	
	const [about, setAbout] = useState({
		applicationName: "Unknown application",
		version: "Unknown version",
		developer: "Unknown"
	});
	
	fetchUtils.fetchJson(`${serviceUrl}/about`).then(({json}) => {
		setAbout(json);
	}).catch(error => {
		console.log('HTTP call failed. Error message:', error)
	});
	
	return (
		<>
			<Button variant="outlined" size="medium"
					sx= {{width: 128, padding: 1, margin: 2}}
						onClick={openDialog}>
				About
			</Button>
			
			<Dialog open={showDialog} onClose={closeDialog} 
				aria-labelledby="alert-dialog-title"
				aria-describedby="alert-dialog-description">
				<DialogTitle id="alert-dialog-title">
					{"About"}
				</DialogTitle>
				<DialogContent>
					<DialogContentText id="alert-dialog-description">
						<strong>Application Name:</strong>{about.applicationName}
						<br/>
						<strong>Version:</strong> {about.version}
						<br/>
						<strong>Developer:</strong> {about.developer}
					</DialogContentText>
				</DialogContent>
				<DialogActions>
					<Button onClick={closeDialog}>CLOSE</Button>
				</DialogActions>
			</Dialog>
		</>
	)
}
