import {
  Admin,
  Resource,
  ListGuesser,
  EditGuesser,
  ShowGuesser,
  Layout,
  fetchUtils
} from "react-admin";

export function getApplicationPages() {
	const applicationPages = new Map([
		['TestDataPage', TestDataPage],
		['AboutPage', AboutPage]
	]);
	
	return applicationPages;
}

const TestDataPage = () => {
	return (
		<h4>Hello, test data page!</h4>
	);
}

const AboutPage = () => {
	return (
		<h4>Hello, about page!</h4>
	);
}
