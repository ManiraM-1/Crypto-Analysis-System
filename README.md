[![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)]()
[![Status: Active](https://img.shields.io/badge/status-active-green.svg)]()
[![License: MIT](https://img.shields.io/badge/license-MIT-blue.svg)]()
[![Hugging Face](https://img.shields.io/badge/%F0%9F%A4%97%20Hugging%20Face-Deployed-orange)](https://huggingface.co/spaces)

# 🔐 Crypto Analysis System

The **Crypto Analysis System** is an enterprise-grade, microservices-based platform designed to analyze cryptocurrency trends in real-time. 

Unlike standard dashboards, this system utilizes a **3-tier architecture** (Frontend, Backend, ML Engine) fully deployed on the cloud. It combines **Spring Boot** for robust API management, **React** for a responsive UI, and **Python (TensorFlow/XGBoost)** for deep-learning-based price predictions.

---

## 🔴 Live Demo

The application is fully deployed and running on **Hugging Face Spaces**.

### 👉 [Click Here to Open the Dashboard](https://YOUR-FRONTEND-SPACE-URL-HERE)
*(Replace the link above with your actual Frontend Space URL)*

---

## 🚀 Key Features

- 📊 **Real-time Market Analysis**: Live tracking of crypto assets via CoinDCX/Binance APIs.
- 🤖 **Hybrid ML Engine**: Combines **LSTM (Deep Learning)** and **XGBoost** for high-confidence trend forecasting.
- 🧠 **Sentiment Analysis**: AI-driven market mood analysis (Fear vs. Greed).
- ⚡ **Microservices Architecture**: Decoupled services for maximum scalability.
- 🛡 **Robust Backend**: Java Spring Boot handling business logic and security.
- 📉 **Technical Indicators**: Automated calculation of RSI, MACD, and Bollinger Bands without external dependencies.

---

## 🏗️ Architecture & Tech Stack

This project follows a **Microservices Architecture** deployed via Docker Containers:

| Component | Technology | Role |
|-----------|------------|------|
| **Frontend** | React (Vite) + TypeScript + Tailwind CSS | Interactive Dashboard & UI |
| **Backend** | Java Spring Boot (Maven) | API Gateway, Business Logic, CORS handling |
| **ML Engine** | Python (Flask, TensorFlow, XGBoost) | Data Processing & Price Prediction Models |
| **Deployment**| Docker + Hugging Face Spaces | Cloud Infrastructure |

### 🔄 System Flow
1. **User** interacts with the **React Frontend**.
2. Frontend requests data from the **Spring Boot Backend**.
3. Backend acts as a gateway, fetching live market data and requesting predictions from the **Python ML Engine**.
4. ML Engine processes data, runs the models, and returns the signal (BUY/SELL/HOLD).

---

## 📦 Project Structure
