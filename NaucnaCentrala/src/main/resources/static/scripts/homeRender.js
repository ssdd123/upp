function render(role) {
	if(role === "editor") {
		editorTabs();
		getDataForEditor();
	} else if(role === "reviewer") {
		reviewerTabs();
		getDataForReviewer();
	} else if(role === "registered") {
		registeredTabs();
		getDataForRegistered();
	}
}
