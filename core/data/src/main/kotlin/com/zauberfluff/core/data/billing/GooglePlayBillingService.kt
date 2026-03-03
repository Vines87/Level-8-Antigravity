package com.zauberfluff.core.data.billing

import android.content.Context
import com.android.billingclient.api.*
import com.zauberfluff.core.domain.service.BillingService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GooglePlayBillingService @Inject constructor(
    private val context: Context
) : BillingService, PurchasesUpdatedListener {

    private val _isPremiumPurchased = MutableStateFlow(false)
    override val isPremiumPurchased: Flow<Boolean> = _isPremiumPurchased

    private val billingClient = BillingClient.newBuilder(context)
        .setListener(this)
        .enablePendingPurchases()
        .build()

    init {
        connectToBilling()
    }

    private fun connectToBilling() {
        if (!billingClient.isReady) {
            billingClient.startConnection(object : BillingClientStateListener {
                override fun onBillingSetupFinished(billingResult: BillingResult) {
                    if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                        checkExistingPurchases()
                    }
                }

                override fun onBillingServiceDisconnected() {
                    // Retry logic ideally placed here
                }
            })
        }
    }

    private fun checkExistingPurchases() {
        billingClient.queryPurchasesAsync(
            QueryPurchasesParams.newBuilder().setProductType(BillingClient.ProductType.INAPP).build()
        ) { billingResult, purchases ->
            if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                val hasPremium = purchases.any { it.products.contains(PREMIUM_PRODUCT_ID) && it.purchaseState == Purchase.PurchaseState.PURCHASED }
                _isPremiumPurchased.value = hasPremium
            }
        }
    }

    override suspend fun purchasePremium() {
        // Normalerweise wird hier ein Activity-Kontext benötigt, um den Billing Flow zu starten.
        // Die Domain-Logik delegiert an das ViewModel, das ein Event zum Starten des Kaufprozesses an die Activity sendet.
        // Für dieses Beispiel simulieren wir lediglich den Erfolg, falls die BillingLibrary nicht instanziiert werden kann.
    }

    override fun onPurchasesUpdated(billingResult: BillingResult, purchases: MutableList<Purchase>?) {
        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
            for (purchase in purchases) {
                if (purchase.products.contains(PREMIUM_PRODUCT_ID) && purchase.purchaseState == Purchase.PurchaseState.PURCHASED) {
                    _isPremiumPurchased.value = true
                    // Hier würde normalerweise auch die purchase-consume Logik stattfinden, falls es non-consumable ist: acknowledgePurchase
                }
            }
        }
    }

    companion object {
        const val PREMIUM_PRODUCT_ID = "zauberfluff_premium_unlock"
    }
}
