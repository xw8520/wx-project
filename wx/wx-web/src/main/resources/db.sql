/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/3/11 18:27:55                           */
/*==============================================================*/


drop table if exists ArticleItem;

drop table if exists Articles;

drop table if exists ImageMessage;

drop table if exists Message;

drop table if exists MessageRecord;

drop table if exists WxTag;

drop table if exists accesstoken;

drop table if exists account;

drop table if exists jsapiticket;

drop table if exists menu;

drop table if exists qrcode;

drop table if exists users;

drop table if exists wxmedia;

/*==============================================================*/
/* Table: ArticleItem                                           */
/*==============================================================*/
create table ArticleItem
(
   id                   int not null auto_increment,
   thumb_media_id       varchar(200) not null,
   author               national varchar(20),
   title                national varchar(200) not null,
   content_source_url   varchar(200),
   content              national varchar(5000) not null,
   digest               national varchar(200),
   show_cover_pic       bool,
   articlesid           int not null,
   primary key (id)
);

/*==============================================================*/
/* Table: Articles                                              */
/*==============================================================*/
create table Articles
(
   id                   int not null auto_increment,
   title                national varchar(20) not null,
   mediaid              varchar(200),
   createtime           datetime,
   remark               national varchar(200),
   accountid            int not null,
   domain               int not null,
   primary key (id)
);

/*==============================================================*/
/* Table: ImageMessage                                          */
/*==============================================================*/
create table ImageMessage
(
   id                   int not null auto_increment,
   title                national varchar(20) not null,
   remark               national varchar(100),
   url                  varchar(200) not null,
   createtime           datetime,
   domain               int not null,
   accountid            int not null,
   primary key (id)
);

/*==============================================================*/
/* Table: Message                                               */
/*==============================================================*/
create table Message
(
   id                   int not null auto_increment,
   title                national varchar(50) not null,
   content              national varchar(500),
   mediaid              varchar(200) not null,
   createtime           datetime,
   stateid              tinyint not null,
   remark               national varchar(200),
   delwx                bool not null,
   accountid            int not null,
   domain               int not null,
   type                 smallint,
   primary key (id)
);

/*==============================================================*/
/* Table: MessageRecord                                         */
/*==============================================================*/
create table MessageRecord
(
   id                   int not null auto_increment,
   title                national varchar(200),
   mid                  int not null,
   accountid            int not null,
   domain               int,
   tagid                int,
   openid               varchar(2000),
   createtime           datetime,
   remark               national varchar(200),
   stateid              tinyint,
   toall                bool,
   msgid                varchar(100),
   primary key (id)
);

/*==============================================================*/
/* Table: WxTag                                                 */
/*==============================================================*/
create table WxTag
(
   id                   int not null auto_increment,
   name                 national varchar(20) not null,
   wxid                 int not null,
   remark               national varchar(200),
   createtime           datetime,
   accountid            int not null,
   domain               int not null,
   primary key (id)
);

/*==============================================================*/
/* Table: accesstoken                                           */
/*==============================================================*/
create table accesstoken
(
   id                   int(11) not null auto_increment,
   accountid            int(11) not null,
   token                varchar(200) character set utf8,
   createtime           datetime not null,
   expiredtime          datetime not null,
   primary key (id)
)
   ENGINE=InnoDB
   DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
   AUTO_INCREMENT=2;

/*==============================================================*/
/* Table: account                                               */
/*==============================================================*/
create table account
(
   id                   int(11) not null auto_increment,
   name                 varchar(100) character set utf8,
   appid                varchar(200) character set utf8,
   secret               varchar(200) character set utf8,
   remark               varchar(200) character set utf8,
   type                 tinyint(4) default NULL,
   createtime           datetime not null,
   modifytime           datetime default NULL,
   domain               int(11) default NULL,
   primary key (id)
)
   ENGINE=InnoDB
   DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
   AUTO_INCREMENT=2;

/*==============================================================*/
/* Table: jsapiticket                                           */
/*==============================================================*/
create table jsapiticket
(
   id                   int(11) not null auto_increment,
   accountid            int(11) not null,
   ticket               varchar(200) character set utf8,
   createtime           datetime not null,
   expiredtime          datetime not null,
   primary key (id)
)
   ENGINE=InnoDB
   DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
   AUTO_INCREMENT=1;

/*==============================================================*/
/* Table: menu                                                  */
/*==============================================================*/
create table menu
(
   id                   int(11) not null auto_increment,
   name                 varchar(50) character set utf8,
   url                  varchar(200) character set utf8,
   type                 tinyint(4) not null comment '0-分组，1-菜单项',
   pid                  int(11) not null,
   domain               int(11) not null,
   createtime           datetime not null,
   modifytime           datetime default NULL,
   status               tinyint(4) not null,
   ordernum             int(11) not null,
   primary key (id)
)
   ENGINE=InnoDB
   DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
   AUTO_INCREMENT=8;

/*==============================================================*/
/* Table: qrcode                                                */
/*==============================================================*/
create table qrcode
(
   id                   int(11) not null auto_increment,
   accountid            int(11) not null,
   param                varchar(20) character set utf8,
   remark               varchar(100) character set utf8,
   createtime           datetime not null,
   expiredtime          datetime not null,
   ticket               varchar(200) character set utf8,
   primary key (id)
)
   ENGINE=InnoDB
   DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
   AUTO_INCREMENT=1;

/*==============================================================*/
/* Table: users                                                 */
/*==============================================================*/
create table users
(
   id                   int(11) not null auto_increment,
   account              varchar(100) character set utf8,
   password             varchar(100) character set utf8,
   name                 varchar(10) character set utf8,
   domain               int(11) not null,
   createtime           datetime not null,
   modifytime           datetime not null,
   company              varchar(50) character set utf8,
   primary key (id)
)
   ENGINE=InnoDB
   DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
   AUTO_INCREMENT=2;

/*==============================================================*/
/* Table: wxmedia                                               */
/*==============================================================*/
create table wxmedia
(
   id                   int(11) not null auto_increment,
   title                varchar(50) character set utf8,
   remark               varchar(200) character set utf8,
   mediatype            tinyint(4) not null,
   accountid            int(11) not null,
   islong               tinyint(1) default 0,
   mediaid              varchar(200) character set utf8,
   createtime           datetime not null,
   domain               int(11) default NULL,
   permanent            bit(1) default NULL,
   expiretime           date default NULL,
   url                  varchar(300) character set utf8,
   primary key (id)
)
   ENGINE=InnoDB
   DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
   AUTO_INCREMENT=3;

