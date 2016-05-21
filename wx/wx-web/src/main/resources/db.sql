/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/5/21 14:04:17                           */
/*==============================================================*/


drop table if exists AccessToken;

drop table if exists Account;

drop table if exists Users;

drop table if exists WxMedia;

drop table if exists jsapiticket;

drop table if exists menu;

drop table if exists qrcode;

/*==============================================================*/
/* Table: AccessToken                                           */
/*==============================================================*/
create table AccessToken
(
   id                   int not null auto_increment,
   accountid            int not null,
   token                national varchar(200) not null,
   createtime           datetime not null,
   expiredtime          datetime not null,
   primary key (id)
);

/*==============================================================*/
/* Table: Account                                               */
/*==============================================================*/
create table Account
(
   id                   int not null auto_increment,
   name                 national varchar(100) not null,
   appid                national varchar(200) not null,
   secret               national varchar(200) not null,
   remark               national varchar(200) not null,
   type                 tinyint,
   createtime           datetime not null,
   modifytime           datetime,
   primary key (id)
);

/*==============================================================*/
/* Table: Users                                                 */
/*==============================================================*/
create table Users
(
   id                   int not null auto_increment,
   account              varchar(100) not null,
   password             varchar(100) not null,
   name                 national varchar(10),
   domain               int not null,
   createtime           datetime not null,
   modifytime           datetime not null,
   company              national varchar(50),
   primary key (id)
);

/*==============================================================*/
/* Table: WxMedia                                               */
/*==============================================================*/
create table WxMedia
(
   id                   int not null auto_increment,
   title                national varchar(50) not null,
   remark               national varchar(200),
   mediatype            tinyint not null,
   accountid            int not null,
   islong               bool not null,
   mediaid              varchar(200),
   createtime           datetime not null,
   domain               int,
   primary key (id)
);

/*==============================================================*/
/* Table: jsapiticket                                           */
/*==============================================================*/
create table jsapiticket
(
   id                   int not null auto_increment,
   accountid            int not null,
   ticket               national varchar(200) not null,
   createtime           datetime not null,
   expiredtime          datetime not null,
   primary key (id)
);

/*==============================================================*/
/* Table: menu                                                  */
/*==============================================================*/
create table menu
(
   id                   int not null auto_increment,
   name                 national varchar(50) not null,
   url                  varchar(200),
   type                 tinyint not null comment '0-分组，1-菜单项',
   pid                  int not null,
   domain               int not null,
   createtime           datetime not null,
   modifytime           datetime,
   status               tinyint not null,
   ordernum             int not null,
   primary key (id)
);

/*==============================================================*/
/* Table: qrcode                                                */
/*==============================================================*/
create table qrcode
(
   id                   int not null auto_increment,
   accountid            int not null,
   param                national varchar(20) not null,
   remark               national varchar(100),
   createtime           datetime not null,
   expiredtime          datetime not null,
   ticket               national varchar(200) not null,
   primary key (id)
);

