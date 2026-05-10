# API Overview 
## Info
API名称：milestone  
目的：milestoneアプリケーションを実行するにあたり必要となるAPI  
バージョン：1.0.0  
更新方針：
  - アプリケーション作成中に修正事項の発生
  - 機能追加の発生

## Server
本番環境URL：TBD  
バージョニング：/v1のようにバージョンに応じたパスをURLに入れる。

## Security
### 認証：  
  - ユーザパスワード認証
    下記リクエスト時はパスワードを必要とする。  
    - ユーザ登録
    - ログイン
  - JWTトークン(トークンの検証によりログインユーザの特定に使用)  

### 認可：JWTトークンから取得するユーザIDでリソースへのアクセスを制限
- JWTトークンは認証後に発行される  
CORS：未定  

## URI rules
- 複数形の名詞を使用
- 複数の単語はハイフンで接続
- 検索関連はクエリパラメータ
- リソースを一意に特定できるものはパスパラメータ
## Request 
### Format
- JSON
### Key name rules
- camel case
## Response
### Format
- JSON
### Key name rules
- camel case
### Status code 
|status | message               | description                               |  
|:------|:----------------------|:------------------------------------------|
|200    | Ok                    | GET/PUT/PATCH成功        　　　　          |
|201    | Created               | POST成功                                  |
|204    | No Content            | DELETE/もしくはレスポンスなしリクエスト成功  |
|400    | Bad Request           | バリデーションエラー/リクエスト形式不足      |
|401    | Unauthorized          | 認証失敗                                   |
|403    | Forbidden             | 認可失敗                                   |
|404    | Not Found             | 対象リソースが存在しない                    |
|405    | Method Not Allowed    | 許可されていないメソッド                    |
|409    | Conflict              | 一意制約違反など(例：Emailアドレス重複)     |
|500    | Internal Server Error | サーバ内部エラー                           |