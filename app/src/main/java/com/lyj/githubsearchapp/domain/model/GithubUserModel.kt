package com.lyj.githubsearchapp.domain.model

/***
 * Presentation Layer에서 사용될 User의 추상화 객체
 */
interface GithubUserModel {
    val userName: String?
    val avatarUrl : String?
}