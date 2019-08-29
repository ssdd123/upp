function addDataToSubmittedArticles(data) {
	var div = $('#submittedArticles');
	var str = '';
	for(i = 0; i < data.length; i++) {
		str += "<div class='divSearchInput' id='divEditor" + data[i].id +"'>"
			+ "<h3>" + data[i].title + "</h3>"
			+ "<p>" 
			+ "<i>" + data[i].author.name + "</i>"
			+ "<p>Scientific field: " + data[i].scientificFieldDTO.name + "</p>"
			+ "<p>Keywords: " + data[i].keyWords + "</p>"
			+ "<p>Abstract: " + data[i].articleAbstract + "</p>"
			+ "<p>Magazine: " + data[i].magazineDTO.title + "</p>"
			+ "</p>"
			+ "<button class='btn btn-success' onClick='approveForPickingFieldEditor(" + data[i].id + ")'>Approve</button>"
			+ "<button class='btn btn-warning' onClick='articleNeedsFormatting(" + data[i].id + ")'>Needs formatting</button>"
			+ "<button class='btn btn-danger' onClick='articleDisaproved(" + data[i].id + ")'>Disapprove</button>"
			+ "</div>"
	}
	div.append(str);
}

function addDataToPickingReviewers(data) {
	var div = $('#pickReviewers');
	var str = '';
	for(i = 0; i < data.length; i++) {
		str += "<div class='divSearchInput' id='divEditor" + data[i].id +"'>"
			+ "<h3>" + data[i].title + "</h3>"
			+ "<p>" 
			+ "<i>" + data[i].author.name + "</i>"
			+ "<p>Scientific field: " + data[i].scientificFieldDTO.name + "</p>"
			+ "<p>Magazine: " + data[i].magazineDTO.title + "</p>"
			+ "</p>";
		
		$.ajax({
			async: false,
			url: "http://localhost:2345/article/getReviewersForScientificField/" + data[i].scientificFieldDTO.id + "/" + data[i].id,
			type: "GET",
			dataType: "json",
			success: function (dataR) {
				for(j=0; j<dataR.length; j++) {
					var reviewer = "<p id='reviewer" + data[i].id + "_" + dataR[j].id + "'>"
						+ dataR[j].name
						+ "<button class='btn btn-primary' onClick='selectReviewer(" + data[i].id + ", " + dataR[j].id + ")'>Pick</button>"
						+ "</p>";
					str += reviewer;
				}
			},
	        error: function (jqXHR, textStatus, errorThrown) {
	        	top.location.href = "login.html";
	        }
		});
		
		str += "</div>"
	}
	div.append(str);
}

function addDataToFirstReview(data) {
	var div = $('#firstReview');
	var str = '';
	for(i = 0; i < data.length; i++) {
		str += "<div class='divSearchInput' id='divForFirstReview" + data[i].id +"'>"
			+ "<h3>" + data[i].title + "</h3>"
			+ "<p>" 
			+ "<i>" + data[i].author.name + "</i>"
			+ "<p>Scientific field: " + data[i].scientificFieldDTO.name + "</p>"
			+ "<p>Keywords: " + data[i].keyWords + "</p>"
			+ "<p>Abstract: " + data[i].articleAbstract + "</p>"
			+ "<p>Magazine: " + data[i].magazineDTO.title + "</p>"
			+ "</p>"
			+ "<input type='text' class='form-control' placeholder='Comment for editor...' id='commentForEditor" + data[i].id + "'>"
			+ "<input type='text' class='form-control' placeholder='Comment for author...' id='commentForAuthor" + data[i].id + "'>"
			+ "<select class='form-control' id='reviewerPickOption" + data[i].id + "'>"
			+ "<option value='approve'>Approve</option>"
			+ "<option value='needsCorrection'>Needs correction</option>"
			+ "<option value='disapprove'>Disapprove</option>"
			+ "</select>"
			+ "<button class='btn btn-primary' onClick='reviewerResponseFirst(" + data[i].id + ")'>Submit</button>"
			+ "</div>"
	}
	div.append(str);
}

function addDataToReviewedForEditorFirst(data) {
	var div = $('#reviewedArticles');
	var str = '';
	for(i = 0; i < data.length; i++) {
		str += "<div class='divSearchInput' id='divEditorChecksFirstReview" + data[i].id +"'>"
			+ "<h3>" + data[i].title + "</h3>"
			+ "<p>" 
			+ "<i>" + data[i].author.name + "</i>"
			+ "<p>Magazine: " + data[i].magazineDTO.title + "</p>"
			+ "</p><br>";
		
		$.ajax({
			async: false,
			url: "http://localhost:2345/articleData/getArticleReviews/" + data[i].id,
			type: "GET",
			dataType: "json",
			success: function (dataR) {
				for(j=0; j<dataR.length; j++) {
					var review = "<p>"
						+ "<i>" + dataR[j].userDTO.name + ": </i>"
						+ dataR[j].commentForEditor
						+ "<p>Reviewer suggestion: " + convertReviewerSuggestion(dataR[j].reviewChoice) + "</p>"
						+ "</p><br>";
					str += review;
				}
			},
	        error: function (jqXHR, textStatus, errorThrown) {
	        	top.location.href = "login.html";
	        }
		});
		str += "<button class='btn btn-success' onClick='articleApproved(" + data[i].id + ")'>Approve</button>"
			+ "<button class='btn btn-warning' onClick='articleNeedsCorrections(" + data[i].id + ")'>Needs corrections</button>"
			+ "<button class='btn btn-danger' onClick='articleDisaprovedSecond(" + data[i].id + ")'>Disapprove</button>"
		str	+= "</div>"
	}
	div.append(str);
}

function addDataToCorrectedForEditor(data) {
	var div = $('#correctedArticles');
	var str = '';
	for(i = 0; i < data.length; i++) {
		str += "<div class='divSearchInput' id='divEditorChecksCorrection" + data[i].id +"'>"
			+ "<h3>" + data[i].title + "</h3>"
			+ "<p>" 
			+ "<i>" + data[i].author.name + "</i>"
			+ "<p>Scientific field: " + data[i].scientificFieldDTO.name + "</p>"
			+ "<p>Keywords: " + data[i].keyWords + "</p>"
			+ "<p>Abstract: " + data[i].articleAbstract + "</p>"
			+ "<p>Magazine: " + data[i].magazineDTO.title + "</p>"
			+ "</p>"
			+ "<button class='btn btn-success' onClick='approveCorrection(" + data[i].id + ")'>Approve</button>"
			+ "<button class='btn btn-warning' onClick='sendCorrectedToReviewer(" + data[i].id + ")'>Send to reviewer</button>"
			+ "<button class='btn btn-danger' onClick='disapproveCorrection(" + data[i].id + ")'>Disapprove</button>"
			+ "</div>"
	}
	div.append(str);
}

function addDataToNeedsCorrection(data) {
	var div = $('#changesNeeded');
	var str = '';
	for(i = 0; i < data.length; i++) {
		str += "<div class='divSearchInput' id='divAuthorCorrectsArticle" + data[i].id +"'>"
			+ "<h3>" + data[i].title + "</h3>"
			+ "<p>" 
			+ "<i>" + data[i].author.name + "</i>"
			+ "<p>Magazine: " + data[i].magazineDTO.title + "</p>"
			+ "</p><br>";
		
		$.ajax({
			async: false,
			url: "http://localhost:2345/articleData/getArticleReviews/" + data[i].id,
			type: "GET",
			dataType: "json",
			success: function (dataR) {
				for(j=0; j<dataR.length; j++) {
					var review = "<p>"
						+ "<i>Review: </i>"
						+ dataR[j].commentForAuthor
						+ "</p><br>";
					str += review;
				}
			},
	        error: function (jqXHR, textStatus, errorThrown) {
	        	top.location.href = "login.html";
	        }
		});
		str += "<button class='btn btn-success' onClick='correctArticleByAuthor(" + data[i].id + ")'>Correct</button>"
		str	+= "</div>"
	}
	div.append(str);
}

function addDataToSecondReview(data) {
	var div = $('#secondReview');
	var str = '';
	for(i = 0; i < data.length; i++) {
		str += "<div class='divSearchInput' id='divEditorChecksCorrection" + data[i].id +"'>"
			+ "<h3>" + data[i].title + "</h3>"
			+ "<p>" 
			+ "<i>" + data[i].author.name + "</i>"
			+ "<p>Scientific field: " + data[i].scientificFieldDTO.name + "</p>"
			+ "<p>Keywords: " + data[i].keyWords + "</p>"
			+ "<p>Abstract: " + data[i].articleAbstract + "</p>"
			+ "<p>Magazine: " + data[i].magazineDTO.title + "</p>"
			+ "</p>"
			+ "<button class='btn btn-success' onClick='approveCorrection(" + data[i].id + ")'>Approve</button>"
			+ "<button class='btn btn-warning' onClick='sendCorrectedToReviewer(" + data[i].id + ")'>Send to reviewer</button>"
			+ "<button class='btn btn-danger' onClick='disapproveCorrection(" + data[i].id + ")'>Disapprove</button>"
			+ "</div>"
	}
	div.append(str);
}

function addDataToNeedsFormatting(data) {
	var div = $('#reformattingNeeded');
	var str = '';
	for(i = 0; i < data.length; i++) {
		str += "<div class='divSearchInput' id='divAuthorFormatsArticle" + data[i].id +"'>"
			+ "<h3>" + data[i].title + "</h3>"
			+ "<p>" 
			+ "<i>" + data[i].author.name + "</i>"
			+ "<p>Scientific field: " + data[i].scientificFieldDTO.name + "</p>"
			+ "<p>Keywords: " + data[i].keyWords + "</p>"
			+ "<p>Abstract: " + data[i].articleAbstract + "</p>"
			+ "<p>Magazine: " + data[i].magazineDTO.title + "</p>"
			+ "</p>"
			+ "<button class='btn btn-success' onClick='format(" + data[i].id + ")'>Format</button>"
			+ "</div>"
	}
	div.append(str);
}

function convertReviewerSuggestion(reviewChoice) {
	if(reviewChoice == "approve") {
		return "Approve";
	} else if (reviewChoice == "needsCorrection") {
		return "Needs correction";
	} else {
		return "Disapprove";
	}
}