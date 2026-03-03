package com.zauberfluff.feature.compliance.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComplianceInfoScreen(
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Privacy Policy && Compliance") },
                navigationIcon = {
                    TextButton(onClick = onNavigateBack) {
                        Text("← Zurück")
                    }
                }
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Zauberfluff - Datenschutzerklärung & Families Policy Info",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Text(
                    text = "1. Keine Datenerfassung\n" +
                            "Wir sammeln, speichern oder übertragen keine persönlichen Daten, Nutzungsstatistiken oder Identifikatoren (wie Werbe-IDs).\n\n" +
                            
                            "2. Keine Werbung\n" +
                            "Diese App ist zu 100% werbefrei. Wir verwenden keine SDKs von Drittanbietern zur Anzeige von Werbung.\n\n" +
                            
                            "3. Keine Analytics\n" +
                            "Wir haben bewusst auf die Einbindung von Google Analytics, Firebase Analytics, Crashlytics oder ähnlichen Tracking-Diensten verzichtet, um die Privatsphäre der Kinder vollständig zu schützen.\n\n" +
                            
                            "4. Elternbereich (Parent Gate)\n" +
                            "Käufe, Absturzberichte (falls optional) und Einstellungen sind durch eine Rechenaufgabe geschützt, um unbeabsichtigte Bedienung durch Kinder zu verhindern.\n\n" +
                            
                            "5. Einhaltung der Google Play Families Policy\n" +
                            "Diese Anwendung wurde unter strikter Einhaltung der Google Play Richtlinien für Familien entwickelt. Dies wird durch den internen 'FamiliesComplianceAudit' bei jedem App-Start sichergestellt.",
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Start
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Button(
                    onClick = onNavigateBack,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("Verstanden")
                }
            }
        }
    }
}
