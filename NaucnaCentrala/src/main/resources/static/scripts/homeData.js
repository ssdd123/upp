function getDataForEditor() {
	$.ajax({
		async: false,
		url: "http://localhost:2345/articleData/submittedForEditor",
		type: "GET",
		dataType: "json",
		success: function (data) {
			addDataToSubmittedArticles(data);
		},
        error: function (jqXHR, textStatus, errorThrown) {
        	top.location.href = "login.html";
        }
	});
	$.ajax({
		async: false,
		url: "http://localhost:2345/articleData/forPickingReviewers",
		type: "GET",
		dataType: "json",
		success: function (data) {
			addDataToPickingReviewers(data);
		},
        error: function (jqXHR, textStatus, errorThrown) {
        	top.location.href = "login.html";
        }
	});
	$.ajax({
		async: false,
		url: "http://localhost:2345/articleData/reviewedForEditorFirst",
		type: "GET",
		dataType: "json",
		success: function(data) {
			addDataToReviewedForEditorFirst(data);
		},
        error: function (jqXHR, textStatus, errorThrown) {
        	top.location.href = "login.html";
        }
	});
	$.ajax({
		async: false,
		url: "http://localhost:2345/articleData/correctedForEditor",
		type: "GET",
		dataType: "json",
		success: function(data) {
			addDataToCorrectedForEditor(data);
		},
        error: function (jqXHR, textStatus, errorThrown) {
        	top.location.href = "login.html";
        }
	});
}

function getDataForReviewer() {
	$.ajax({
		async: false,
		url: "http://localhost:2345/articleData/forFirstReview",
		type: "GET",
		dataType: "json",
		success: function(data) {
			addDataToFirstReview(data);
		},
        error: function (jqXHR, textStatus, errorThrown) {
        	top.location.href = "login.html";
        }
	});
}

function getDataForRegistered() {
	$.ajax({
		async: false,
		url: "http://localhost:2345/articleData/needsFormatting",
		type: "GET",
		dataType: "json",
		success: function(data) {
			addDataToNeedsFormatting(data);
		},
        error: function (jqXHR, textStatus, errorThrown) {
        	top.location.href = "login.html";
        }
	});
	$.ajax({
		async: false,
		url: "http://localhost:2345/articleData/needsCorrection",
		type: "GET",
		dataType: "json",
		success: function(data) {
			addDataToNeedsCorrection(data);
		},
        error: function (jqXHR, textStatus, errorThrown) {
        	top.location.href = "login.html";
        }
	});
	$.ajax({
		async: false,
		url: "http://localhost:2345/magazine/getAll",
		type: "GET",
		dataType: "json",
		success: function(data) {
			var div = $('#addNewArticle');
			div.empty();
			for(i = 0; i < data.length; i++) {
				var magazineDiv = "<div>"
					+ "<h4>"
					+ data[i].title
					+ "</h4>"
					+ "<button class='btn btn-primary' onClick='pickMagazine(" + data[i].id + ")'>Pick</button>"
					+ "</div>";
				div.append(magazineDiv);
			}
		},
        error: function (jqXHR, textStatus, errorThrown) {
        	top.location.href = "login.html";
        }
	});
}