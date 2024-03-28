CREATE TABLE IF NOT EXISTS SPRINGBOOT_JEREMY_PRODUCT
(
    product_id         INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    product_name       VARCHAR(128) NOT NULL,
    category           VARCHAR(32)  NOT NULL,
    image_url          VARCHAR(256) ,
    price              INT          ,
    stock              INT          ,
    description        VARCHAR(1024),
    created_date       TIMESTAMP    ,
    last_modified_date      TIMESTAMP
);
CREATE TABLE IF NOT EXISTS SPRINGBOOT_JEREMY_USER
(
    user_id            INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    email              VARCHAR(256) NOT NULL UNIQUE,
    password           VARCHAR(256) NOT NULL,
    created_date       TIMESTAMP    NOT NULL,
    last_modified_date TIMESTAMP    NOT NULL
);
CREATE TABLE IF NOT EXISTS SPRINGBOOT_JEREMY_ORDER
(
    order_id           INT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
    user_id            INT       NOT NULL,
    total_amount       INT       NOT NULL,
    created_date       TIMESTAMP NOT NULL,
    last_modified_date TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS SPRINGBOOT_JEREMY_ORDER_ITEM
(
    order_item_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    order_id      INT NOT NULL,
    product_id    INT NOT NULL,
    quantity      INT NOT NULL,
    amount        INT NOT NULL
);