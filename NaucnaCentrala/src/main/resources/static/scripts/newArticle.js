function pickMagazine(magazineId) {
	$.ajax({
		async: false,
		url: "http://localhost:2345/article/checkMagazineType/" + magazineId,
		type: "POST",
		success: function(data) {
			var div = $('#addNewArticle');
			div.empty();
			var inputFields = "<input type='text' class='form-control' id='newArticleTitle' placeholder='Title...'>"
				+ "<input type='text' class='form-control' id='newArticleKeywords' placeholder='Keywords...'>"
				+ "<input type='text' class='form-control' id='newArticleAbstract' placeholder='Abstract...'>";
				
			$.ajax({
				async: false,
				url: "http://localhost:2345/magazineScientificField/getByMagazineId/" + magazineId,
				type: "GET",
				dataType: "json",
				success: function(dataSF) {
					var scientificFieldPick = "<select class='form-control' id='newArticleScientificField'>";
					for(i=0; i<dataSF.length; i++) {
						scientificFieldPick += "<option value='" + dataSF[i].scientificFieldDTO.id + "'>" + dataSF[i].scientificFieldDTO.name + "</option>";
					}
					scientificFieldPick += "</select>";
					inputFields += scientificFieldPick;
				},
		        error: function (jqXHR, textStatus, errorThrown) {
		        	alert(errorThrown)
		        }
			});
			
			var coauthorDiv = "<br>Coauthors:" +
					"<div id='coauthorDiv'>" +
					"<button class='btn btn-primary' onClick='addCoauthorInputs()'>Add more coauthors</button>" +
					"</div>"; 
			
			div.append(inputFields);
			div.append(coauthorDiv);
			
			addCoauthorInputs();
			
			div.append("<br/><br/><button class='btn btn-success' onClick='submitNewArticle()'>Submit</button>");
		},
        error: function (jqXHR, textStatus, errorThrown) {
        	alert(errorThrown)
        }
	});
}

function submitNewArticle() {
	var title = $('#newArticleTitle').val();
	var keywords = $('#newArticleKeywords').val();
	var articleAbstract = $('#newArticleAbstract').val();
	var scientificField = $('#newArticleScientificField').val();
	var coauthors = [];
	
	var coauthorNames = $('.coauthorName');
	var coauthorEmails = $('.coauthorEmail');
	var coauthorCities = $('.coauthorCity');
	var coauthorCountries = $('.coauthorCountry');
	
	for(i=0; i<coauthorNames.length; i++) {
		coauthors.push({ 
			"name": coauthorNames[i].value, 
			"email": coauthorEmails[i].value, 
			"city": coauthorCities[i].value,
			"country": coauthorCountries[i].value
		});
	}
	
	var newArt = {
		"title": title,
		"keyWords": keywords,
		"articleAbstract": articleAbstract,
		"scientificFieldDTO": { "id": scientificField },
		"coauthors": coauthors
	}
	
	$.ajax({
		async: false,
		url: "http://localhost:2345/article/submitNewArticle",
		type: "POST",
		contentType: "application/json",
		data: JSON.stringify(newArt),
		success: function(data) {
			alert('wfcw')
		},
        error: function (jqXHR, textStatus, errorThrown) {
        	alert(errorThrown)
        }
	});
}

function approveForPickingFieldEditor(articleId) {
	$.ajax({
		async: false,
		url: "http://localhost:2345/article/approveForPickingFieldEditor/" + articleId,
		type: "PUT",
		success: function(data) {
			$('#divEditor' + articleId).remove();
			alert('pick editor')
		},
        error: function (jqXHR, textStatus, errorThrown) {
        	alert(errorThrown)
        }
	});
}

function articleNeedsFormatting(articleId) {
	$.ajax({
		async: false,
		url: "http://localhost:2345/article/articleNeedsFormatting/" + articleId,
		type: "PUT",
		success: function(data) {
			$('#divEditor' + articleId).remove();
			alert('needs formatting')
		},
        error: function (jqXHR, textStatus, errorThrown) {
        	alert(errorThrown)
        }
	});
}

function articleDisaproved(articleId) {
	$.ajax({
		async: false,
		url: "http://localhost:2345/article/articleDisaproved/" + articleId,
		type: "PUT",
		success: function(data) {
			$('#divEditor' + articleId).remove();
			alert('disaproved')
		},
        error: function (jqXHR, textStatus, errorThrown) {
        	alert(errorThrown)
        }
	});
}

function articleDisaprovedSecond(articleId) {
	$.ajax({
		async: false,
		url: "http://localhost:2345/article/articleDisaproved/" + articleId,
		type: "PUT",
		success: function(data) {
			$('#divEditorChecksFirstReview' + articleId).remove();
			alert('disaproved')
		},
        error: function (jqXHR, textStatus, errorThrown) {
        	alert(errorThrown)
        }
	});
}

function selectReviewer(articleId, reviewerId) {
	$.ajax({
		async: false,
		url: "http://localhost:2345/article/addReviewer/" + articleId + "/" + reviewerId,
		type: "PUT",
		success: function(data) {
			$('#reviewer' + articleId + "_" + reviewerId).remove();
			alert('added reviewer');
		},
        error: function (jqXHR, textStatus, errorThrown) {
        	alert(errorThrown)
        }
	});
}

function reviewerResponseFirst(articleId) {
	var commentForEditor = $('#commentForEditor' + articleId).val();
	var commentForAuthor = $('#commentForAuthor' + articleId).val();
	var reviewerPickOption = $('#reviewerPickOption' + articleId).val();
	
	var review = {
		"commentForEditor": commentForEditor,
		"commentForAuthor": commentForAuthor,
		"reviewChoice": reviewerPickOption
	}
	
	$.ajax({
		async: false,
		url: "http://localhost:2345/article/addReviewFirst/" + articleId,
		type: "POST",
		contentType: "application/json",
		data: JSON.stringify(review),
		success: function(data) {
			$('#divForFirstReview' + articleId).remove();
			alert('added review');
		},
        error: function (jqXHR, textStatus, errorThrown) {
        	alert(errorThrown)
        }
	});
}

function articleApproved(articleId) {
	$.ajax({
		async: false,
		url: "http://localhost:2345/article/articleAproved/" + articleId,
		type: "PUT",
		success: function(data) {
			$('#divEditorChecksFirstReview' + articleId).remove();
			alert('aproved')
		},
        error: function (jqXHR, textStatus, errorThrown) {
        	alert(errorThrown)
        }
	});
}

function articleNeedsCorrections(articleId) {
	$.ajax({
		async: false,
		url: "http://localhost:2345/article/articleNeedsCorrection/" + articleId,
		type: "PUT",
		success: function(data) {
			$('#divEditorChecksFirstReview' + articleId).remove();
			alert('needs correction')
		},
        error: function (jqXHR, textStatus, errorThrown) {
        	alert(errorThrown)
        }
	});
}

function correctArticleByAuthor(articleId) {
	$.ajax({
		async: false,
		url: "http://localhost:2345/article/corrected/" + articleId,
		type: "PUT",
		success: function(data) {
			$('#divAuthorCorrectsArticle' + articleId).remove();
			alert('corrected')
		},
        error: function (jqXHR, textStatus, errorThrown) {
        	alert(errorThrown)
        }
	});
}

function sendCorrectedToReviewer(articleId) {
	$.ajax({
		async: false,
		url: "http://localhost:2345/article/sendCorrectedToReviewer/" + articleId,
		type: "PUT",
		success: function(data) {
			$('#divEditorChecksCorrection' + articleId).remove();
			alert('sent to reviewer')
		},
        error: function (jqXHR, textStatus, errorThrown) {
        	alert(errorThrown)
        }
	});
}

function approveCorrection(articleId) {
	$.ajax({
		async: false,
		url: "http://localhost:2345/article/articleAproved/" + articleId,
		type: "PUT",
		success: function(data) {
			$('#divEditorChecksCorrection' + articleId).remove();
			alert('aproved')
		},
        error: function (jqXHR, textStatus, errorThrown) {
        	alert(errorThrown)
        }
	});
}

function disapproveCorrection(articleId) {
	$.ajax({
		async: false,
		url: "http://localhost:2345/article/articleDisaproved/" + articleId,
		type: "PUT",
		success: function(data) {
			$('#divEditorChecksCorrection' + articleId).remove();
			alert('disaproved')
		},
        error: function (jqXHR, textStatus, errorThrown) {
        	alert(errorThrown)
        }
	});
}

function format(articleId) {
	$.ajax({
		async: false,
		url: "http://localhost:2345/article/format/" + articleId,
		type: "PUT",
		success: function(data) {
			$('#divAuthorFormatsArticle' + articleId).remove();
			alert('formatted')
		},
        error: function (jqXHR, textStatus, errorThrown) {
        	alert(errorThrown)
        }
	});
}

function addCoauthorInputs() {
	var coauthorDiv = $('#coauthorDiv');
	var inputs = "<input type='text' class='form-control coauthorName' placeholder='Name...'>"
		+ "<input type='text' class='form-control coauthorEmail' placeholder='Email...'>"
		+ "<input type='text' class='form-control coauthorCity' placeholder='City...'>"
		+ "<input type='text' class='form-control coauthorCountry' placeholder='Country...'><br/>";
	coauthorDiv.prepend(inputs);
}
