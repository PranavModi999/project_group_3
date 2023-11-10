package com.example.project_group_3

class User {
    var UId: String? = null
    var userType: String? = null
    var userName: String? = null
    var userEmail: String? = null
    var userAddress: String? = null
    var userPostal: String? = null

    constructor() {
        // Needed for Firebase serialization
    }

    constructor(
        UId: String?,
        userName: String?,
        userEmail: String?,
        userAddress: String?,
        userPostal: String?,
        userType: String?
    ) {
        this.UId = UId
        this.userName = userName
        this.userEmail = userEmail
        this.userAddress = userAddress
        this.userPostal = userPostal
        this.userType = userType
    }
}