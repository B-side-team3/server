CREATE TABLE `member` (
`member_id`	INT(11)	NOT NULL	COMMENT 'pk',
`email`	VARCHAR(20)	NOT NULL	COMMENT '이메일 주소',
`nickname`	VARCHAR(20)	NOT NULL	COMMENT '닉네임',
`image_url`	TEXT	NULL	COMMENT '프로필 이미지',
`is_deleted` boolean	NULL	DEFAULT NULL	COMMENT '삭제일자',
`created_date`	DATETIME	NOT NULL	COMMENT '생성일자'
)
COLLATE='utf8_general_ci'
ENGINE=INNODB
;

ALTER TABLE `member` ADD CONSTRAINT `PK_MEMBER` PRIMARY KEY (
`member_id`
);

CREATE TABLE `oauth` (
`oauth_id`	INT(11)	NOT NULL	COMMENT 'pk',
`member_id`	INT(11)	NOT NULL	COMMENT 'member_id',
`type`	VARCHAR(20)	NOT NULL	COMMENT '로그인타입(카카오,)',
`access_token`	TEXT	NULL	COMMENT 'access token',
`refresh_token`	TEXT	NOT NULL	COMMENT 'refresh token',
`access_time`	DATETIME	NULL	DEFAULT NULL	COMMENT '최초 접근 일자'
)
COLLATE='utf8_general_ci'
ENGINE=INNODB
;

ALTER TABLE `oauth` ADD CONSTRAINT `PK_oauth` PRIMARY KEY (
`oauth_id`,
`member_id`
);

ALTER TABLE `oauth` ADD CONSTRAINT `FK_member_TO_oauth` FOREIGN KEY (
`member_id`
)
REFERENCES `member` (
`member_id`
);
