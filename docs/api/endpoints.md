# Auth
## POST /auth/login
- [] 作成済み  
### Description
ログイン
### Request
- Rules  

|Key                  |Nullable   |Required  |Description                   |
|:--------------------|:----------|:---------|:-----------------------------|
|email                |No         |Yes       |                              |
|password             |No         |Yes       |                              |

- Request body
```json
{
  "email":"example@co.jp",
  "password":"PassW0rd!"
}  
```
### Response body
- 200 OK
```json
{
  "data":{
    "token":"xxxxxxxxxxxxxxxxxx.zzzzzzzzzzzzzzz.yyyyyyyyy"
  },
  "meta":{
    "statusCode":200,
    "statusMessage":"Ok"
  }
}
```
- 400 Bad Request
```json
{
  "data":null,
  "meta":{
    "statusCode":400,
    "statusMessage":"Bad Request"
  }
}
```
### Headers :
#### Request
- Content-Type: application/json
#### Response
- Content-Type: application/json



# Users
## POST /users  
- [ ] Responseでトークン・名前・写真を戻す仕様に変更する(code側の修正をResponse body(201)に合わせる)
### Description
ユーザ登録
### Request
- Rules  

|Key                  |Nullable   |Required  |Description                   |
|:--------------------|:----------|:---------|:-----------------------------|
|name                 |No         |Yes       |                              |
|photo                |Yes        |No        |                              |
|birthday             |Yes        |No        |                              |
|email                |No         |Yes       | email format, length 6..30   |
|password             |No         |Yes       | length 8..20, must include uppercase/lowercase/digit/special |

- Request body
```json
{
  "name":"taro",
  "photo":"xxxxx.jpeg",
  "birthday":"yyyy-mm-dd",
  "email":"example@co.jp",
  "password":"passW0rd1!"
}  
```
### Response body  
- 201 Created
```json
{
  "data":{
    "name":"taro",
    "photo":"xxxxx.jpeg",
    "token":"xxxxxxxxxxxxxxxxxx.zzzzzzzzzzzzzzz.yyyyyyyyy"
  },
  "meta":{
    "statusCode":201,
    "statusMessage":"Created"
  }
}
```
- 400 Bad Request
```json
{
  "data":null,
  "meta":{
    "statusCode":400,
    "statusMessage":"Bad Request"
  }
}
```
### Headers :
#### Request
- Content-Type: application/json
#### Response
- Content-Type: application/json



## PUT /users/me  
- [ ] Requestの内容からemail・passwordを除外する(code側)
- [ ] pathsの修正(/meを追加)
### Description
ログインユーザ情報の変更  
- フロント側(UI)は
### Request
- Rules  

|Key                  |Nullable   |Required  |Description                   |
|:--------------------|:----------|:---------|:-----------------------------|
|name                 |No         |Yes       |                              |
|photo                |Yes        |No        |                              |
|birthday             |Yes        |No        |                              |
※空ボディは400とする

- Request body
```json
{
  "name":"taro",
  "photo":"xxxxx.jpeg",
  "birthday":"yyyy-mm-dd"
}  
```
### Response body  
- 200 Ok
```json
{
  "data":{
    "name":"taro",
    "photo":"xxxxx.jpeg",
    "birthday":"yyyy-mm-dd"
  },
  "meta":{
    "statusCode":200,
    "statusMessage":"Ok"
  }
}
```
- 400 Bad Request
```json
{
  "data":null,
  "meta":{
    "statusCode":400,
    "statusMessage":"Bad Request"
  }
}
```
### Headers :
#### Request
- Content-Type: application/json
- Authorization: Bearer xxxxxxxxxxxxxxxxxx.zzzzzzzzzzzzzzz.yyyyyyyyy
#### Response
- Content-Type: application/json



## PATCH /users/me/email  
- [ ] Responseの実装を行う。
- [ ] pathsの修正(/meを追加)
### Description
Emailの更新
### Request
- Rules  

|Key                  |Nullable   |Required  |Description                   |
|:--------------------|:----------|:---------|:-----------------------------|
|currentEmail         |No         |Yes       | email format, length 6..30   |
|newEmail             |No         |Yes       | email format, length 6..30　 |
|password             |No         |Yes       | length 8..20, must include uppercase/lowercase/digit/special |

- Request body
```json
{
  "currentEmail":"example@co.jp",
  "newEmail":"new_example@co.jp",
  "password":"passW0rd1!"
}  
```
### Response body  
- 200 Ok
```json
{
  "data":null,
  "meta":{
    "statusCode":200,
    "statusMessage":"Ok"
  }
}
```
- 400 Bad Request
```json
{
  "data":null,
  "meta":{
    "statusCode":400,
    "statusMessage":"Bad Request"
  }
}
```
### Headers :
#### Request
- Content-Type: application/json
- Authorization: Bearer xxxxxxxxxxxxxxxxxx.zzzzzzzzzzzzzzz.yyyyyyyyy
#### Response
- Content-Type: application/json



## PATCH /users/me/password
- [ ] Responseの実装を行う。
- [ ] pathsの修正(/meを追加)
### Description
Passwordの更新
### Request
- Rules  

|Key                  |Nullable   |Required  |Description                   |
|:--------------------|:----------|:---------|:-----------------------------|
|email                |No         |Yes       | email format, length 6..30   |
|currentPassword      |No         |Yes       | length 8..20, must include uppercase/lowercase/digit/special |
|newPassword          |No         |Yes       | length 8..20, must include uppercase/lowercase/digit/special |

- Request body
```json
{
  "email":"example@co.jp",
  "currentPassword":"passW0rd1!",
  "newPassword":"new_passW0rd1!"
}  
```
### Response body  
- 200 Ok
```json
{
  "data":null,
  "meta":{
    "statusCode":200,
    "statusMessage":"Ok"
  }
}
```
- 400 Bad Request
```json
{
  "data":null,
  "meta":{
    "statusCode":400,
    "statusMessage":"Bad Request"
  }
}
```
### Headers :
#### Request
- Content-Type: application/json
- Authorization: Bearer xxxxxxxxxxxxxxxxxx.zzzzzzzzzzzzzzz.yyyyyyyyy
#### Response
- Content-Type: application/json



## GET /users/me
- [ ] pathに対してmeを追加 
- [ ] updatedAtとdeletedAtは戻さない仕様に変更する。
  →createdAtはログインユーザが個人のプロフィール欄を見た際にアカウント作成してからどのくらいの
  期間使用しているか見えたほうがUXが向上すると感じた。ログインボーナスやアカウント作成日を残しているアプリは多い印象。
  しかし、updatedAtやdeletedAtについてはログインユーザに対して必要ではない情報になると考えた。
### Description
ログインユーザの情報取得
### Request
- ログイン後に付与されるJWTを使用してユーザ情報を取得するためRequest bodyは不要とする。
### Response body  
- 200 Ok
```json
{
  "data":{
    "name":"taro",
    "photo":"xxxxx.jpeg",
    "birthday":"yyyy-mm-dd",
    "createdAt":"yyyy-mm-dd"
  },
  "meta":{
    "statusCode":200,
    "statusMessage":"Ok"
  }
}
```
- 400 Bad Request
```json
{
  "data":null,
  "meta":{
    "statusCode":400,
    "statusMessage":"Bad Request"
  }
}
```
### Headers :
#### Request
- Authorization: Bearer xxxxxxxxxxxxxxxxxx.zzzzzzzzzzzzzzz.yyyyyyyyy
#### Response
- Content-Type: application/json



## DELETE /users/me
- [ ] Request bodyの修正を行う(code側)
- [ ] pathsの修正(/meを追加)
### Description
ユーザ削除(退会)
### Request
- Rules  

|Key                  |Nullable   |Required  |Description                   |
|:--------------------|:----------|:---------|:-----------------------------|
|password             |No         |Yes       | length 8..20, must include uppercase/lowercase/digit/special |

- Request body
```json
{
  "password":"passW0rd1!"
}  
```
### Response body  
- 200 Ok
```json
{
  "data":null,
  "meta":{
    "statusCode":200,
    "statusMessage":"Ok"
  }
}
```
- 400 Bad Request
```json
{
  "data":null,
  "meta":{
    "statusCode":400,
    "statusMessage":"Bad Request"
  }
}
```
### Headers :
#### Request
- Content-Type: application/json
- Authorization: Bearer xxxxxxxxxxxxxxxxxx.zzzzzzzzzzzzzzz.yyyyyyyyy
#### Response
- Content-Type: application/json



# Goals
## POST /goals
- [ ] 
### Description
ゴールの登録
### Request
- Rules  

|Key                  |Nullable   |Required  |Description                   |
|:--------------------|:----------|:---------|:-----------------------------|
|title                |No         |Yes       | length1..30                  |
|content              |No         |Yes       | length max 150               |
|dueDate              |No         |Yes       |                              |

- Request body
```json
{
  "title":"xxxxxxx",
  "content":"xxxxxxxxxxx",
  "dueDate":"yyyy-mm-dd"
}  
```
### Response body  
- 201 Created
```json
{
  "data":{
    "title":"xxxxxxx",
    "content":"xxxxxxxxx",
    "dueDate":"yyyy-mm-dd",
  },
  "meta":{
    "statusCode":201,
    "statusMessage":"Created"
  }
}
```
- 400 Bad Request
```json
{
  "data":null,
  "meta":{
    "statusCode":400,
    "statusMessage":"Bad Request"
  }
}
```
### Headers :
#### Request
- Content-Type: application/json
#### Response
- Content-Type: application/json


## PUT /goals/{id}
- [ ] ResponseDTOを使用して戻り値を設定すること。
### Description
ゴールの編集
### Request
- Rules  

|Key                  |Nullable   |Required  |Description                   |
|:--------------------|:----------|:---------|:-----------------------------|
|id                   |No         |Yes       | query path                   | 
|title                |No         |Yes       | length1..30                  |
|content              |No         |Yes       | length max 150               |
|dueDate              |No         |Yes       |                              |

- Request body
```json
{
  "title":"xxxxxxxx",
  "content":"xxxxxxxxxxx",
  "dueDate":"yyyy-mm-dd"
}  
```
### Response body  
- 201 Created
```json
{
  "data":{
    "id":"~~~",
    "title":"xxxxxxxx",
    "content":"xxxxxxxxx",
    "dueDate":"yyyy-mm-dd",
  },
  "meta":{
    "statusCode":201,
    "statusMessage":"Created"
  }
}
```
- 400 Bad Request
```json
{
  "data":null,
  "meta":{
    "statusCode":400,
    "statusMessage":"Bad Request"
  }
}
```
### Headers :
#### Request
- Content-Type: application/json
#### Response
- Content-Type: application/json




## GET /goals/{id}
- [ ] GoalResponseのuserIdプロパティは削除すること。  
フロント側で直接UserIdを保有するタイミングはなくJWTトークンのみで扱うようにしているため。
### Description
ゴールの取得(詳細表示)
### Request
- path parameter  

|Key                  |Nullable   |Required  |Description                   |
|:--------------------|:----------|:---------|:-----------------------------|
|id                   |No         |Yes       | Goal ID                      | 

### Response body  
- 200 OK
```json
{
  "data":{
    "id":"~~~",
    "title":"xxxxxxxx",
    "content":"xxxxxxxxx",
    "dueDate":"yyyy-mm-dd",
  },
  "meta":{
    "statusCode":201,
    "statusMessage":"Created"
  }
}
```
- 400 Bad Request
```json
{
  "data":null,
  "meta":{
    "statusCode":400,
    "statusMessage":"Bad Request"
  }
}
```
### Headers :
#### Request
- Content-Type: application/json
#### Response
- Content-Type: application/json

## GET /goals/
- [ ] homeで表示するための優先度が高い順3つを表示する。  
### Description
ゴールの取得(優先度高を3つ表示)
### Request
- path parameter  

|Key                  |Nullable   |Required  |Description                   |
|:--------------------|:----------|:---------|:-----------------------------|
|id                   |No         |Yes       | Goal ID                      | 

### Response body  
- 200 OK
```json
{
  "data":[
    {
      "id":"~~~",
      "title":"xxxxxxxx",
      "dueDate":"yyyy-mm-dd"
    },
    {
      "id":"~~~",
      "title":"xxxxxxxx",
      "dueDate":"yyyy-mm-dd"
    },
    {
      "id":"~~~",
      "title":"xxxxxxxx",
      "dueDate":"yyyy-mm-dd"
    }
  ],
  "meta":{
    "statusCode":201,
    "statusMessage":"Created"
  }
}
```
- 400 Bad Request
```json
{
  "data":null,
  "meta":{
    "statusCode":400,
    "statusMessage":"Bad Request"
  }
}
```
### Headers :
#### Request
- Content-Type: application/json
#### Response
- Content-Type: application/json




- memo
  ### ゴール
- 削除
- 表示（一覧／詳細）