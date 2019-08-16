package com.wisdom.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invitation {
	private Integer forumId;
	private String ownerId;
	private String ownerName;
	private String ownerHeadicon;
	private String theme;
	private String content;
	private String img;
	private String forumDate;
	private List<Comment> comments;
}
