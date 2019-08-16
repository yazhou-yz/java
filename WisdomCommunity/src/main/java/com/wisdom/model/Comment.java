package com.wisdom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
	private Integer commentId;
	private String content;
	private String ownerId;
	private String ownerName;
	private String ownerHeadicon;
	private String commentDate;
	private Integer invitationId;
}
