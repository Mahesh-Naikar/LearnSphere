package com.learnsphere.services;

import java.util.List;

import com.learnsphere.entity.Comments;

public interface CommentService {
	
	List<Comments> commentList();
	String addComment(Comments comment);

}
