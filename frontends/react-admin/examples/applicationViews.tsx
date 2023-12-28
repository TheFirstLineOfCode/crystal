import Button from '@mui/material/Button';
import {fetchUtils} from 'react-admin';
import {getServiceUrl} from './crystalAdmin'
import {AboutView} from './aboutView'

export function getApplicationViews() {
	const applicationViews = new Map([
		['TestDataView', TestDataView],
		['AboutView', AboutView]
	]);
	
	return applicationViews;
}

const TestDataView = () => {
	return (
		<h4>Hello, test data view!</h4>
	);
}

