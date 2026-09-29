package com.example.kmpweather

// STEP 1: `expect` declares an API in common code without implementing it.
// Each platform source set must provide a matching `actual` implementation.
expect fun platformName(): String
