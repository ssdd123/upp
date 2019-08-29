function editorTabs() {
	var tabs = "<li class='nav-item'><a id='submittedArticles-tab' class='nav-link' data-toggle='tab' role='tab' href='#submittedArticles'>Submitted articles</a></li>"
		+ "<li class='nav-item'><a id='pickReviewers-tab' class='nav-link' data-toggle='tab' role='tab' href='#pickReviewers'>Pick reviewers</a></li>"
		+ "<li class='nav-item'><a id='reviewedArticles-tab' class='nav-link' data-toggle='tab' role='tab' href='#reviewedArticles'>Reviewed articles</a></li>"
		+ "<li class='nav-item'><a id='correctedArticles-tab' class='nav-link' data-toggle='tab' role='tab' href='#correctedArticles'>Corrected articles</a></li>"
		+ "<li class='nav-item'><a class='nav-link logout' data-toggle='tab' role='tab' onClick='return logout()'>Logout</a></li>";
		
	var divs = "<div class='tab-pane fade' id='submittedArticles' role='tabpanel' aria-labelledby='submittedArticles-tab'></div>"
		+ "<div class='tab-pane fade' id='pickReviewers' role='tabpanel' aria-labelledby='pickReviewers-tab'></div>"
		+ "<div class='tab-pane fade' id='reviewedArticles' role='tabpanel' aria-labelledby='reviewedArticles-tab'></div>"
		+ "<div class='tab-pane fade' id='correctedArticles' role='tabpanel' aria-labelledby='correctedArticles-tab'></div>";
		
	addToHtml(tabs, divs);
}

function reviewerTabs() {
	var tabs = "<li class='nav-item'><a id='firstReview-tab' class='nav-link' data-toggle='tab' role='tab' href='#firstReview'>First review</a></li>"
		+ "<li class='nav-item'><a id='secondReview-tab' class='nav-link' data-toggle='tab' role='tab' href='#secondReview'>Second review</a></li>"
		+ "<li class='nav-item'><a class='nav-link logout' data-toggle='tab' role='tab' onClick='return logout()'>Logout</a></li>";

	var divs = "<div class='tab-pane fade' id='firstReview' role='tabpanel' aria-labelledby='firstReview-tab'>firstReview</div>"
		+ "<div class='tab-pane fade' id='secondReview' role='tabpanel' aria-labelledby='secondReview-tab'>secondReview</div>";
	
	addToHtml(tabs, divs);
}

function registeredTabs() {
	var tabs = "<li class='nav-item'><a id='reformattingNeeded-tab' class='nav-link' data-toggle='tab' role='tab' href='#reformattingNeeded'>Reformatting needed</a></li>"
		+ "<li class='nav-item'><a id='changesNeeded-tab' class='nav-link' data-toggle='tab' role='tab' href='#changesNeeded'>Changes needed</a></li>"
		+ "<li class='nav-item'><a id='addNewArticle-tab' class='nav-link' data-toggle='tab' role='tab' href='#addNewArticle'>Add new article</a></li>"
		+ "<li class='nav-item'><a class='nav-link logout' data-toggle='tab' role='tab' onClick='return logout()'>Logout</a></li>";

	var divs = "<div class='tab-pane fade' id='reformattingNeeded' role='tabpanel' aria-labelledby='reformattingNeeded-tab'>reformattingNeeded</div>"
		+ "<div class='tab-pane fade' id='changesNeeded' role='tabpanel' aria-labelledby='changesNeeded-tab'>changesNeeded</div>"
		+ "<div class='tab-pane fade' id='addNewArticle' role='tabpanel' aria-labelledby='addNewArticle-tab'>addNewArticle</div>";
	
	addToHtml(tabs, divs);
}

function addToHtml(tabs, divs) {
	$('#homeTabs').empty()
	$('#homeContent').empty()
	$('#homeTabs').append(tabs)
	$('#homeContent').append(divs)
}