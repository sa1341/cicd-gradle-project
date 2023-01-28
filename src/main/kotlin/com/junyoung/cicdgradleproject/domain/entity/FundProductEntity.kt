package com.junyoung.cicdgradleproject.domain.entity

import com.junyoung.cicdgradleproject.domain.common.AuditEntity
import org.hibernate.annotations.DynamicUpdate
import org.jetbrains.annotations.NotNull
import java.math.BigDecimal
import java.math.BigInteger
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@DynamicUpdate
@Table(name = "fund_product")
data class FundProductEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fund_product_id")
    val fundProductId: Long? = null,
    /**
     * 펀드코드
     */
    @field:NotNull
    @Column(name = "fnd_cod")
    val fundCode: String,

    /**
     * 펀드원장상태구분코드
     */
    @field:NotNull
    @Column(name = "fnd_ledg_stat_tcd")
    val ledgerStatus: String,

    /**
     * 펀드개인신규계약가능여부
     */
    @field:NotNull
    @Column(name = "fnd_pers_new_cvnt_able_yn", columnDefinition = "BIT")
    val newCovenantAvailable: Boolean,

    /**
     * 펀드개인추가매수가능여부
     */
    @field:NotNull
    @Column(name = "fnd_pers_ad_buy_able_yn", columnDefinition = "BIT")
    val additionalBuyAvailable: Boolean,

    /**
     * 환매신청 가능여부
     */
    @field:NotNull
    @Column(name = "fnd_sell_able_yn", columnDefinition = "BIT")
    val fundSellAvailable: Boolean,

    /**
     * 펀드예탁원펀드코드
     */
    @field:NotNull
    @Column(name = "fnd_ksd_fnd_cod")
    val ksdFundCode: String,

    /**
     * 펀드예탁원운용펀드코드
     */
    @field:NotNull
    @Column(name = "fnd_ksd_or_fnd_cod")
    val ksdOperationFundCode: String,

    /**
     * 펀드금융투자협회펀드코드
     */
    @field:NotNull
    @Column(name = "fnd_kofia_fnd_cod")
    val kofiaFundCode: String,

    /**
     * 펀드평가평가회사기준펀드코드
     */
    @field:NotNull
    @Column(name = "fnd_ev_ev_co_bas_fnd_cod")
    val evalCorpFundCode: String,

    /**
     * 펀드상품한글명
     */
    @field:NotNull
    @Column(name = "fnd_pd_kor_nm")
    val productName: String,

    /**
     * 펀드상품영문명
     */
    @field:NotNull
    @Column(name = "fnd_pd_eng_nm")
    val engProductName: String,

    /**
     * 펀드예탁원운용펀드명
     */
    @field:NotNull
    @Column(name = "fnd_ksd_or_fnd_nm")
    val ksdOperationFundName: String,

    /**
     * 펀드운용회사코드
     */
    @field:NotNull
    @Column(name = "fnd_or_co_cod")
    val operationCorpCode: String,

    /**
     * 펀드운용회사명
     */
    @field:NotNull
    @Column(name = "fnd_or_co_nm")
    val operationCorpName: String,

    /**
     * 약관파일경로명
     */
    @Column(name = "stpl_file_path_nm")
    val stipulationFileUrl: String?,

    /**
     * 집합투자규약파일경로명
     */

    /**
     * 투자설명서파일경로명
     */
    @Column(name = "ivst_mnl_file_path_nm")
    val investManualFileUrl: String?,

    /**
     * 간이투자설명서파일경로명
     */
    @Column(name = "srp_file_path_nm")
    val simpleManualFileUrl: String?,

    /**
     * 자산운용보고서파일경로명
     */
    @Column(name = "ast_or_rpt_path_nm")
    val assetOperationReportPath: String?,

    /**
     * 펀드총보수율
     */
    @Column(name = "fnd_trw_r")
    val totalRewardRate: BigDecimal?,

    /**
     * 펀드운용보수율
     */
    @Column(name = "fnd_or_rwrd_r")
    val operationRewardRate: BigDecimal?,

    /**
     * 펀드판매보수율
     */
    @Column(name = "fnd_sale_rwrd_r")
    val saleRewardRate: BigDecimal?,

    /**
     * 펀드수탁보수율
     */
    @Column(name = "fnd_trs_rwrd_r")
    val trustRewardRate: BigDecimal?,

    /**
     * 펀드일반사무관리보수율
     */
    @Column(name = "fnd_gnrl_ofwk_mt_rwrd_r")
    val officeWorkRewardRate: BigDecimal?,

    /**
     * 펀드판매개시일
     */
    @field:NotNull
    @Column(name = "fnd_sale_strt_dt")
    val fundSaleStartDate: LocalDate,

    /**
     * 펀드처리기준일자
     */
    @Column(name = "fnd_prc_bas_dt")
    val processBaseDate: LocalDate?,

    /**
     * 펀드거래기준가
     */
    @Column(name = "fnd_tr_bpr")
    val txBasePrice: BigDecimal?,

    /**
     * 펀드수정기준가
     */
    @Column(name = "fnd_altr_bpr")
    val alterBasePrice: BigDecimal?,

    /**
     * 설정일 이후 누적 수익률
     */
    @Column(name = "fnd_after_accm_ern_r")
    val afterEarningRate: BigDecimal?,

    /**
     * 펀드1일누적수익률
     */
    @field:NotNull
    @Column(name = "fnd_dy1_accm_ern_r")
    val dailyEarningRate: BigDecimal?,

    /**
     * 펀드1주차누적수익률
     */
    @field:NotNull
    @Column(name = "fnd_wk1st_accm_ern_r")
    val weeklyEarningRate: BigDecimal?,

    /**
     * 펀드1개월누적수익률
     */
    @field:NotNull
    @Column(name = "fnd_mon1_accm_ern_r")
    val monthlyEarningRate: BigDecimal?,

    /**
     * 펀드3개월누적수익률
     */
    @field:NotNull
    @Column(name = "fnd_mon3_accm_ern_r")
    val quarterEarningRate: BigDecimal?,

    /**
     * 펀드6개월누적수익률
     */
    @field:NotNull
    @Column(name = "fnd_mon6_accm_ern_r")
    val halfYearEarningRate: BigDecimal?,

    /**
     * 펀드YTD누적수익률
     */
    @field:NotNull
    @Column(name = "fnd_ytd_accm_ern_r")
    val yearToDateEarningRate: BigDecimal?,

    /**
     * 펀드1년누적수익률
     */
    @field:NotNull
    @Column(name = "fnd_yr1_accm_ern_r")
    val oneYearEarningRate: BigDecimal?,

    /**
     * 펀드2년누적수익률
     */
    @field:NotNull
    @Column(name = "fnd_yr2_accm_ern_r")
    val twoYearEarningRate: BigDecimal?,

    /**
     * 펀드3년누적수익률
     */
    @field:NotNull
    @Column(name = "fnd_yr3_accm_ern_r")
    val threeYearEarningRate: BigDecimal?,

    /**
     * 펀드전체판매사설정원본잔액
     */
    @Column(name = "fnd_al_selr_set_org_ba")
    val allSellerOriginalBalance: BigDecimal?,

    /**
     * 펀드당일설정잔고좌수
     */
    @Column(name = "fnd_tdy_set_bal_sq")
    val todaySettingBalanceSeq: BigInteger?,

    /**
     * 펀드설정일자
     */
    @Column(name = "fnd_set_dt")
    val settingDate: LocalDate?,

    /**
     * 펀드상품투자위험분류코드
     */
    @field:NotNull
    @Column(name = "fnd_fss_pd_ivst_rsk_ccd")
    val riskClassificationCode: String,

    /**
     * 펀드매수LATETRADING적용시각
     */
    @field:NotNull
    @Column(name = "fnd_buy_le_aplc_tmd")
    val lateTradingApplyTime: String,

    /**
     * 펀드시간내매수기준가적용일수
     */
    @field:NotNull
    @Column(name = "fnd_intm_buy_bpr_aplc_dys")
    val inTimeBuyBasePriceApplyDays: Int,

    /**
     * 펀드시간외매수기준가적용일수
     */
    @field:NotNull
    @Column(name = "fnd_ovtm_buy_bpr_aplc_dys")
    val overTimeBuyBasePriceApplyDays: Int,

    /**
     * 펀드시간내매수결제일수
     */
    @field:NotNull
    @Column(name = "fnd_intm_buy_stl_dys")
    val inTimeBuySettleDays: Int,

    /**
     * 펀드시간외매수결제일수
     */
    @field:NotNull
    @Column(name = "fnd_ovtm_buy_stl_dys")
    val overTimeBuySettleDays: Int,

    /**
     * 펀드환매LATETRADING적용시각
     */
    @field:NotNull
    @Column(name = "fnd_rpc_le_aplc_tmd")
    val sellLateTradingApplyTime: String,

    /**
     * 펀드시간내환매기준가적용일수
     */
    @field:NotNull
    @Column(name = "fnd_intm_rpc_bpr_aplc_dys")
    val inTimeSellBasePriceApplyDays: Int,

    /**
     * 펀드시간외환매기준가적용일수
     */
    @field:NotNull
    @Column(name = "fnd_ovtm_rpc_bpr_aplc_dys")
    val overTimeSellBasePriceApplyDays: Int,

    /**
     * 펀드시간내환매결제일수
     */
    @field:NotNull
    @Column(name = "fnd_intm_rpc_stl_dys")
    val inTimeSellSettleDays: Int,

    /**
     * 펀드시간외환매결제일수
     */
    @field:NotNull
    @Column(name = "fnd_ovtm_rpc_stl_dys")
    val overTimeSellSettleDays: Int,

    /**
     * 펀드클래스1차구분코드
     */
    @field:NotNull
    @Column(name = "fnd_clas_th1_tcd")
    val fundClass1code: String,

    /**
     * 펀드클래스2차구분코드
     */
    @field:NotNull
    @Column(name = "fnd_clas_th2_tcd")
    val fundClass2code: String,

    /**
     * 펀드클래스3차구분코드
     */
    @field:NotNull
    @Column(name = "fnd_clas_th3_tcd")
    val fundClass3code: String,

    /**
     * 펀드클래스1차구분코드명
     */
    @field:NotNull
    @Column(name = "fnd_clas_th1_tcd_nm")
    val fundClass1codeName: String,

    /**
     * 펀드클래스2차구분코드명
     */
    @field:NotNull
    @Column(name = "fnd_clas_th2_tcd_nm")
    val fundClass2codeName: String,

    /**
     * 펀드클래스3차구분코드명
     */
    @field:NotNull
    @Column(name = "fnd_clas_th3_tcd_nm")
    val fundClass3codeName: String,

    /**
     * 해당 펀드에 투자한 고객수
     */
    @field:NotNull
    @Column(name = "fnd_ivst_cs_acq")
    val fundInvestmentCustomerCount: Long,

    /**
     * 해당 펀드에 투자 누적 금액(순매수금액아님)
     */
    @field:NotNull
    @Column(name = "fnd_ivst_pca_accm_a")
    val fundInvestmentAccumulateAmount: BigDecimal,

    /**
     * 펀드파생상품구분코드
     */
    @field:NotNull
    @Column(name = "fnd_drvs_tcd")
    val derivativeCode: String,

    /**
     * 펀드ELF당사타사구분코드
     */
    @field:NotNull
    @Column(name = "fnd_elf_thco_otco_tcd")
    val elfCode: String,

    /**
     * 투자전락
     */
    @Column(name = "fnd_ivst_stgy_memo_info_cn")
    val investmentStrategy: String? = null,

    /**
     * 핵심위험
     */
    @Column(name = "fnd_cr_rsk_memo_info_cn")
    val keyRisks: String? = null,

    /**
     * 고난도적정성상품구분코드
     */
    @Column(name = "hilv_appr_pd_tcd")
    val highLevelAppropriatenessCode: String? = null,

    /**
     * 펀드1개월투자원금누적금액
     */
    @Column(name = "fnd_mon1_buy_amt")
    val monthlyBuyAmount: BigDecimal? = null,

    /**
     * 펀드3개월투자원금누적금액
     */
    @Column(name = "fnd_mon3_buy_amt")
    val quarterBuyAmount: BigDecimal? = null,

    /**
     * 펀드6개월투자원금누적금액
     */
    @Column(name = "fnd_mon6_buy_amt")
    val halfYearBuyAmount: BigDecimal? = null,

    /**
     * 펀드1년투자원금누적금액
     */
    @Column(name = "fnd_yr1_buy_amt")
    val oneYearBuyAmount: BigDecimal? = null,

    /**
     * 펀드1개월표준편차
     */
    @Column(name = "fnd_mon1_dev")
    val monthlyDeviation: BigDecimal? = null,

    /**
     * 펀드3개월표준편차
     */
    @Column(name = "fnd_mon3_dev")
    val quarterDeviation: BigDecimal? = null,

    /**
     * 펀드6개월표준편차
     */
    @Column(name = "fnd_mon6_dev")
    val halfYearDeviation: BigDecimal? = null,

    /**
     * 펀드1년표준편차
     */
    @Column(name = "fnd_yr1_dev")
    val oneYearDeviation: BigDecimal? = null,

    /**
     * 펀드1개월 누적 설정액
     */
    @Column(name = "fnd_mon1_set_amt")
    val monthlySetAmount: BigDecimal? = null,

    /**
     * 펀드3개월 누적 설정액
     */
    @Column(name = "fnd_mon3_set_amt")
    val quarterSetAmount: BigDecimal? = null,

    /**
     * 펀드6개월 누적 설정액
     */
    @Column(name = "fnd_mon6_set_amt")
    val halfYearSetAmount: BigDecimal? = null,

    /**
     * 펀드1년 누적 설정액
     */
    @Column(name = "fnd_yr1_set_amt")
    val oneYearSetAmount: BigDecimal? = null,

    /**
     * 펀드세제상품종류구분코드
     */
    @Column(name = "fnd_txstm_pd_knd_tcd")
    val taxProductCode: String? = null,

    /**
     * 펀드수익률기준일자
     */
    @Column(name = "fnd_ern_r_bas_dt")
    val earningBaseDate: LocalDate? = null,

    /**
     * 펀드원금기준일자
     */
    @Column(name = "fnd_buy_amt_bas_dt")
    val buyAmountBaseDate: LocalDate? = null,

    /**
     * 펀드설정액기준일자
     */
    @Column(name = "fnd_set_amt_bas_dt")
    val setAmountBaseDate: LocalDate? = null,

    /**
     * 펀드표준편차기준일자
     */
    @Column(name = "fnd_dev_bas_dt")
    val deviationBaseDate: LocalDate? = null
) : AuditEntity()
