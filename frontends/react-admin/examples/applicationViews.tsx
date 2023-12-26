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

const AboutView = () => {
	return (
		<h4>Hello, about view!</h4>
	);
}
