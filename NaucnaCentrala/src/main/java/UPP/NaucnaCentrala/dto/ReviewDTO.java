package UPP.NaucnaCentrala.dto;

public class ReviewDTO {
	
	private String commentForEditor;
	private String commentForAuthor;
	private String reviewChoice;
	private UserDTO userDTO;
	
	public ReviewDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getCommentForEditor() {
		return commentForEditor;
	}

	public void setCommentForEditor(String commentForEditor) {
		this.commentForEditor = commentForEditor;
	}

	public String getCommentForAuthor() {
		return commentForAuthor;
	}

	public void setCommentForAuthor(String commentForAuthor) {
		this.commentForAuthor = commentForAuthor;
	}

	public String getReviewChoice() {
		return reviewChoice;
	}

	public void setReviewChoice(String reviewChoice) {
		this.reviewChoice = reviewChoice;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

}
