package com.zauberfluff.feature.tutorial

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zauberfluff.core.ui.pulseClickable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TutorialScreen(
    viewModel: TutorialViewModel = viewModel(),
    onTutorialComplete: () -> Unit
) {
    val currentStep by viewModel.currentStep.collectAsState()
    val isFinished by viewModel.isFinished.collectAsState()
    val totalSteps = viewModel.totalSteps

    if (isFinished) {
        // In a real app we'd navigate away
        onTutorialComplete()
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD)) // Light kid-friendly blue
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        
        Text(
            text = "Zauberfluff Tutorial",
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color(0xFF1565C0)
        )
        
        Spacer(modifier = Modifier.height(32.dp))

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            AnimatedContent(
                targetState = currentStep,
                label = "TutorialSteps"
            ) { step ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    when (step) {
                        0 -> TutorialStepContent("Willkommen!", "Sammle Karten, um Missionen zu erfüllen. \nWer zuerst 6 Punkte hat, gewinnt!")
                        1 -> TutorialStepContent("Missionen", "Du siehst deine Mission immer oben. \nFinde die richtigen Karten!")
                        2 -> TutorialStepContent("Karten ziehen", "Du darfst höchstens 5 Karten haben. \nTippe, um eine neue zu ziehen!")
                        3 -> TutorialStepContent("Gewinnen!", "Sobald du genug Karten hast, \ntippe auf 'Mission erfüllen'. Viel Glück!")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Progress indicators
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            for (i in 0 until totalSteps) {
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(if (i == currentStep) Color(0xFF1565C0) else Color(0xFFBBDEFB))
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Actions
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(64.dp)
                .clip(RoundedCornerShape(32.dp))
                .background(Color(0xFFFFB300))
                .pulseClickable {
                    viewModel.nextStep()
                }
        ) {
            Text(
                text = if (currentStep == totalSteps - 1) "Los geht's!" else "Weiter",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
fun TutorialStepContent(title: String, text: String) {
    Column(
        modifier = Modifier.padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Placeholder for illustration
        Box(
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .background(Color(0xFFFFECB3)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Bild", fontSize = 24.sp, color = Color(0xFFFF8F00))
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2C3E50),
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = text,
            fontSize = 18.sp,
            color = Color(0xFF546E7A),
            textAlign = TextAlign.Center,
            lineHeight = 28.sp
        )
    }
}
