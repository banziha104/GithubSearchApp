//[app](../../../index.md)/[com.lyj.githubsearchapp.data.source.local.dao](../index.md)/[GithubFavoriteUserDao](index.md)/[insertOrDeleteIfExists](insert-or-delete-if-exists.md)

# insertOrDeleteIfExists

[androidJvm]\
open fun [insertOrDeleteIfExists](insert-or-delete-if-exists.md)(entity: [GithubFavoriteUserEntity](../../com.lyj.githubsearchapp.data.source.local.entity/-github-favorite-user-entity/index.md)): Single&lt;[CommitResult](../../com.lyj.githubsearchapp.domain.repository/-commit-result/index.md)&gt;

동일한 즐겨찾기가 존재하는 경우 삭제, 아닌 경우 추가

#### Return

추가, 삭제, 실패 여부

## Parameters

androidJvm

| | |
|---|---|
| entity |  |
