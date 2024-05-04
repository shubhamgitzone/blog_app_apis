package com.shubham.blog.services;

import com.shubham.blog.payloads.CommentDTO;

public interface CommentService {

	CommentDTO createComment(CommentDTO commentDto, Integer postId, Integer userId);
	
	void deleteComment(Integer commentId);
}
