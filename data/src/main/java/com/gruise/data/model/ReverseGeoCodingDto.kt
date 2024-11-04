package com.gruise.data.model

data class ReverseGeoCodingDto(
    val status: Status? = null,
    val results: List<Result>? = null
)

data class Status(
    val code: Int,
    val name: String,
    val message: String
)

data class Result(
    val name: String,
    val code: Code,
    val region: Region
)

data class Code(
    val id: String,
    val type: String,
    val mappingId: String
)

data class Region(
    val area0: Area? = null,
    val area1: Area? = null,
    val area2: Area? = null,
    val area3: Area? = null
)

data class Area(
    val name: String,
    val coords: Coords,
    val alias: String? = null // `alias`는 필요에 따라 nullable 처리
)

data class Coords(
    val center: Center
)

data class Center(
    val crs: String,
    val x: Double,
    val y: Double
)