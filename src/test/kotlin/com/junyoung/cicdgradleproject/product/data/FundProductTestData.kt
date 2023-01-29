package com.junyoung.cicdgradleproject.product.data

import com.junyoung.cicdgradleproject.domain.product.FundProductEntity
import com.junyoung.cicdgradleproject.domain.product.meta.MetaFundProductEntity
import java.math.BigDecimal
import java.math.BigInteger
import java.time.LocalDate
import java.time.LocalDateTime

internal val fundProductEntity = FundProductEntity(
    fundCode = "260005",
    ledgerStatus = "10",
    newCovenantAvailable = true,
    additionalBuyAvailable = true,
    fundSellAvailable = true,
    ksdFundCode = "KSF10214212",
    ksdOperationFundCode = "ksdOperationFundCode",
    kofiaFundCode = "kofiaFundCode",
    evalCorpFundCode = "evalCorpFundCode",
    productName = "키움똑똑이",
    engProductName = "engProductName",
    ksdOperationFundName = "ksdOperationFundName",
    operationCorpCode = "operationCorpCode",
    operationCorpName = "operationCorpName",
    stipulationFileUrl = "stipulationFileUrl",
    investManualFileUrl = "investManualFileUrl",
    simpleManualFileUrl = "simpleManualFileUrl",
    assetOperationReportPath = "assetOperationReportPath",
    totalRewardRate = BigDecimal.TEN,
    operationRewardRate = BigDecimal.TEN,
    saleRewardRate = BigDecimal.TEN,
    trustRewardRate = BigDecimal.TEN,
    officeWorkRewardRate = BigDecimal.TEN,
    fundSaleStartDate = LocalDate.now(),
    processBaseDate = LocalDate.now(),
    txBasePrice = BigDecimal.TEN,
    alterBasePrice = BigDecimal.TEN,
    afterEarningRate = BigDecimal.TEN,
    dailyEarningRate = BigDecimal.TEN,
    weeklyEarningRate = BigDecimal.TEN,
    monthlyEarningRate = BigDecimal.TEN,
    quarterEarningRate = BigDecimal.TEN,
    halfYearEarningRate = BigDecimal.TEN,
    yearToDateEarningRate = BigDecimal.TEN,
    oneYearEarningRate = BigDecimal.TEN,
    twoYearEarningRate = BigDecimal.TEN,
    threeYearEarningRate = BigDecimal.TEN,
    allSellerOriginalBalance = BigDecimal.TEN,
    todaySettingBalanceSeq = BigInteger.TEN,
    settingDate = LocalDate.now(),
    riskClassificationCode = "riskClassificationCode",
    lateTradingApplyTime = "lateTradingApplyTime",
    inTimeBuyBasePriceApplyDays = 1,
    overTimeBuyBasePriceApplyDays = 1,
    inTimeBuySettleDays = 1,
    overTimeBuySettleDays = 1,
    sellLateTradingApplyTime = "sellLateTradingApplyTime",
    inTimeSellBasePriceApplyDays = 2,
    overTimeSellBasePriceApplyDays = 2,
    inTimeSellSettleDays = 2,
    overTimeSellSettleDays = 2,
    fundClass1code = "fundClass1code",
    fundClass2code = "fundClass2code",
    fundClass3code = "fundClass3code",
    fundClass1codeName = "fundClass1codeName",
    fundClass2codeName = "fundClass2codeName",
    fundClass3codeName = "fundClass3codeName",
    fundInvestmentCustomerCount = 10L,
    fundInvestmentAccumulateAmount = BigDecimal.TEN,
    derivativeCode = "derivativeCode",
    elfCode = "elfCode",
    investmentStrategy = "investmentStrategy",
    keyRisks = "keyRisks",
    highLevelAppropriatenessCode = "highLevelAppropriatenessCode",
    monthlyBuyAmount = BigDecimal.TEN,
    quarterBuyAmount = BigDecimal.TEN,
    halfYearBuyAmount = BigDecimal.TEN,
    oneYearBuyAmount = BigDecimal.TEN,
    monthlyDeviation = BigDecimal.TEN,
    quarterDeviation = BigDecimal.TEN,
    halfYearDeviation = BigDecimal.TEN,
    oneYearDeviation = BigDecimal.TEN,
    monthlySetAmount = BigDecimal.TEN,
    quarterSetAmount = BigDecimal.TEN,
    halfYearSetAmount = BigDecimal.TEN,
    oneYearSetAmount = BigDecimal.TEN,
    taxProductCode = "taxProductCode"
)

internal val metaFundProductEntity = MetaFundProductEntity(
    metaFundProductId = 1234L,
    title = "키움똑똑이",
    fundProduct = fundProductEntity,
    createdAt = LocalDateTime.now(),
    createdBy = "jean",
    updatedBy = "jean"
)