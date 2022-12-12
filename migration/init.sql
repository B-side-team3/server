CREATE TABLE `member` (
    `member_id`	INT(11)	NOT NULL AUTO_INCREMENT COMMENT 'member pk',
    `email`	VARCHAR(20)	NOT NULL COMMENT '이메일 주소',
    `nickname`	VARCHAR(20)	NOT NULL COMMENT '닉네임',
    `image_url`	TEXT NULL COMMENT '프로필 이미지',
    `is_deleted` TINYINT  NOT NULL DEFAULT '0'  COMMENT '삭제 여부',
    `is_admin` TINYINT NOT NULL DEFAULT '0' COMMENT '관리자 여부',
    `created_date` TIMESTAMP NOT NULL DEFAULT current_timestamp() COMMENT '생성일자',
    PRIMARY KEY (`member_id`) USING BTREE,
    INDEX `email` (`email`) USING BTREE
)
COLLATE='utf8_general_ci'
ENGINE=INNODB
;

CREATE TABLE `oauth` (
    `oauth_id`	INT(11)	NOT NULL AUTO_INCREMENT COMMENT 'oauth pk',
    `member_id`	INT(11)	NOT NULL COMMENT 'member_id',
    `type`	VARCHAR(20)	NOT NULL COMMENT '로그인 타입',
    `access_token` TEXT NOT NULL COMMENT 'access token',
    `refresh_token`	TEXT NOT NULL COMMENT 'refresh token',
    `is_deleted` TINYINT  NOT NULL DEFAULT '0'  COMMENT '삭제 여부',
    `created_date` TIMESTAMP NOT NULL DEFAULT current_timestamp() COMMENT '생성일자',
    PRIMARY KEY (`oauth_id`) USING BTREE,
    INDEX `member_id` (`member_id`) USING BTREE,
    CONSTRAINT `FK_oauth_member` FOREIGN KEY (`member_id`) REFERENCES `db`.`member` (`member_id`) ON UPDATE NO ACTION ON DELETE NO ACTION
)
COLLATE='utf8_general_ci'
ENGINE=INNODB
;

CREATE TABLE `category` (
    `category_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'category pk',
    `title`	VARCHAR(20)	NOT NULL COMMENT '카테고리 이름',
    `created_date` TIMESTAMP NOT NULL DEFAULT current_timestamp() COMMENT '생성일자',
    PRIMARY KEY (`category_id`) USING BTREE
)
COLLATE='utf8_general_ci'
ENGINE=INNODB
;

CREATE TABLE `routine` (
    `routine_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'routine pk',
    `category_id` INT(11) NOT NULL COMMENT 'category pk',
    `title`	VARCHAR(50)	NOT NULL COMMENT '루틴 이름',
    `description` VARCHAR(200) NOT NULL COMMENT '루틴 설명',
    `imageUrl` TEXT	NULL COMMENT '루틴 이미지',
    `period` INT(11) NOT NULL COMMENT '루틴 기간',
    `start_time` VARCHAR(50) NULL COMMENT '루틴 시작 시간',
    `anchor` VARCHAR(50) NULL COMMENT '루틴 앵커',
    `total_time` INT(11) NOT NULL DEFAULT '0' COMMENT '총 소요 시간',
    `created_date` TIMESTAMP NOT NULL DEFAULT current_timestamp() COMMENT '생성일자',
    PRIMARY KEY (`routine_id`) USING BTREE,
    INDEX `category_id` (`category_id`) USING BTREE,
    CONSTRAINT `FK_routine_category` FOREIGN KEY (`category_id`) REFERENCES `db`.`category` (`category_id`) ON UPDATE NO ACTION ON DELETE NO ACTION
)
COLLATE='utf8_general_ci'
ENGINE=INNODB
;

CREATE TABLE `task` (
    `task_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'task pk',
    `routine_id` INT(11) NOT NULL COMMENT 'routine pk',
    `title`	VARCHAR(50)	NOT NULL COMMENT 'task 이름',
    `expected_time`	INT(11)	NOT NULL DEFAULT '0' COMMENT '예상 시간',
    `created_date` TIMESTAMP NOT NULL DEFAULT current_timestamp() COMMENT '생성일자',
    PRIMARY KEY (`task_id`) USING BTREE,
    INDEX `routine_id` (`routine_id`) USING BTREE,
    CONSTRAINT `FK_task_routine` FOREIGN KEY (`routine_id`) REFERENCES `db`.`routine` (`routine_id`) ON UPDATE NO ACTION ON DELETE NO ACTION
)
COLLATE='utf8_general_ci'
ENGINE=INNODB
;

CREATE TABLE `member_routine` (
    `member_routine_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'member_routine_id pk',
    `member_id` INT(11) NOT NULL COMMENT 'member_id ',
    `routine_id` INT(11) NOT NULL COMMENT 'routine pk',
    `start_date` DATE NOT NULL COMMENT '시작일자',
    `end_date` DATE NOT NULL COMMENT '종료일자',
    `start_time` VARCHAR(50) NULL COMMENT '루틴 시작 시간',
    `anchor` VARCHAR(50) NULL COMMENT '루틴 앵커',
    `total_time` INT(11) NULL COMMENT '총 소요시간',
    `created_date` TIMESTAMP NOT NULL DEFAULT current_timestamp() COMMENT '생성일자',
    PRIMARY KEY (`member_routine_id`) USING BTREE,
    INDEX `member_id` (`member_id`) USING BTREE,
    CONSTRAINT `FK_member_routine_member` FOREIGN KEY (`member_id`) REFERENCES `db`.`member` (`member_id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT `FK_member_routine_routine` FOREIGN KEY (`routine_id`) REFERENCES `db`.`routine` (`routine_id`) ON UPDATE NO ACTION ON DELETE NO ACTION
)
COLLATE='utf8_general_ci'
ENGINE=INNODB
;

CREATE TABLE `member_task` (
    `member_task_id` INT(11) NOT NULL AUTO_INCREMENT COMMENT 'member_task pk',
    `task_id` INT(11) NOT NULL COMMENT 'task_id',
    `member_id` INT(11) NOT NULL COMMENT 'member_id',
    `expected_time` INT(11)	NOT NULL COMMENT '예상 시간',
    `actual_time` INT(11) NOT NULL COMMENT '실제 시간',
    `status` VARCHAR(20) NOT NULL DEFAULT 'completed' COMMENT 'ongoing/completed/uncompleted',
    `is_deleted` TINYINT(4) NOT NULL DEFAULT '0' COMMENT '제외 여부',
    `created_date` TIMESTAMP NOT NULL DEFAULT current_timestamp() COMMENT '생성일자',
    PRIMARY KEY (`member_task_id`) USING BTREE,
    INDEX `member_id` (`member_id`) USING BTREE,
    CONSTRAINT `FK_member_task_member` FOREIGN KEY (`member_id`) REFERENCES `db`.`member` (`member_id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT `FK_member_task_task` FOREIGN KEY (`task_id`) REFERENCES `db`.`task` (`task_id`) ON UPDATE NO ACTION ON DELETE NO ACTION
)
COLLATE='utf8_general_ci'
ENGINE=INNODB
;

