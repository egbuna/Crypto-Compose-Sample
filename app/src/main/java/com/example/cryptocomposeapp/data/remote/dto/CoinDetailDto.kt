package com.example.cryptocomposeapp.data.remote.dto

import com.example.cryptocomposeapp.domain.model.CoinDetail

data class CoinDetailDto(
    val contract: String,
    val contracts: List<Contract>,
    val description: String,
    val development_status: String,
    val first_data_at: String,
    val hardware_wallet: Boolean,
    val hash_algorithm: String,
    val id: String,
    val is_active: Boolean,
    val is_new: Boolean,
    val last_data_at: String,
    val links: Links,
    val links_extended: List<LinksExtended>,
    val message: String?,
    val name: String,
    val open_source: Boolean,
    val org_structure: String?,
    val parent: Parent?,
    val platform: String?,
    val proof_type: String?,
    val rank: Int?,
    val started_at: String?,
    val symbol: String?,
    val tags: List<Tag>,
    val team: List<TeamMember>,
    val type: String?,
    val whitepaper: Whitepaper
)

fun CoinDetailDto.toCoinDetail(): CoinDetail {
    return CoinDetail(
        description,
        development_status,
        first_data_at,
        hardware_wallet,
        id,
        is_active,
        is_new,
        last_data_at,
        links,
        links_extended,
        message,
        name,
        open_source,
        org_structure,
        parent,
        platform,
        proof_type,
        rank,
        started_at,
        symbol,
        tags,
        team,
        type
    )
}