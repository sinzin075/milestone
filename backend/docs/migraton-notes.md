# Migration Notes

## Flywayを使わなかった理由

- Flyway v9.22.3 は MySQL 8.0 に非対応だった
- Spring Boot 3.4.4 との組み合わせでアプリ起動時に例外（Unsupported Database: MySQL 8.0）
- Flywayのバージョンアップも試したが、互換性問題で断念

## 対処方針

- DDLは SQL ファイルとして `/home/shoki/milestone/milestone/src/main/resources/db/migration/` の配下に管理
- DB初期化時は `source schema.sql` を使って手動実行でもOK
- 将来的にMySQLのバージョンを揃えてFlywayに移行する可能性あり
