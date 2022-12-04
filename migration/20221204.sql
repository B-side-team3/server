CREATE TABLE `member` (
`member_id`	INT(11)	NOT NULL AUTO_INCREMENT COMMENT 'pk',
`email`	VARCHAR(20)	NOT NULL COMMENT '이메일 주소',
`nickname`	VARCHAR(20)	NOT NULL COMMENT '닉네임',
`image_url`	TEXT NULL COMMENT '프로필 이미지',
`is_deleted` TINYINT  NOT NULL DEFAULT '0'  COMMENT '삭제일자',
`is_admin` TINYINT NOT NULL DEFAULT '0' COMMENT '관리자 여부',
`created_date` TIMESTAMP NOT NULL DEFAULT current_timestamp() COMMENT '생성일자',
PRIMARY KEY (`member_id`) USING BTREE,
INDEX `email` (`email`) USING BTREE
)
COLLATE='utf8_general_ci'
ENGINE=INNODB
;

CREATE TABLE `oauth` (
`oauth_id`	INT(11)	NOT NULL AUTO_INCREMENT COMMENT 'pk',
`member_id`	INT(11)	NOT NULL	COMMENT 'member_id',
`type`	VARCHAR(20)	NOT NULL	COMMENT '로그인타입(카카오,)',
`access_token`	TEXT NOT NULL	COMMENT 'access token',
`refresh_token`	TEXT NOT NULL	COMMENT 'refresh token',
`access_time`	DATETIME NOT NULL	COMMENT '최초 접근 일자',
PRIMARY KEY (`oauth_id`) USING BTREE,
INDEX `oauth_id` (`oauth_id`) USING BTREE,
INDEX `member_id` (`member_id`) USING BTREE,
CONSTRAINT `FK_oauth_member` FOREIGN KEY (`member_id`) REFERENCES `db`.`member` (`member_id`) ON UPDATE NO ACTION ON DELETE NO ACTION
)
COLLATE='utf8_general_ci'
ENGINE=INNODB
;
