import { List, Datagrid, TextField, ReferenceField } from "react-admin";

export const PostListView = () => (
	<List>
		<Datagrid rowClick="show">
			<ReferenceField source="userId" reference="users" />
			<TextField source="id" />
			<TextField source="title" />
		</Datagrid>
	</List>
);