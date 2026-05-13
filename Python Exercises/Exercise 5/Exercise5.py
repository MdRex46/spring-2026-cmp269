import pandas as pd
import numpy as np

def get_messy_market_data():
    return pd.DataFrame({
        "Date": ["Mon", "Tue", "Wed", "Thu", "Fri"],
        "Open": [200.0, 202.5, np.nan, 201.0, 205.0],
        "Close": [203.0, np.nan, 199.0, 204.5, 208.0],
        "Volume": [1500000, 1800000, 1200000, np.nan, 2100000]
    })

def task_1_data_cleaning():
    print("--- Task 1: Data Cleaning ---")
    df = get_messy_market_data()

    print(df.isnull().sum())

    df['Volume'] = df['Volume'].fillna(0)

    df = df.dropna()

    print(df)
    return df

def task_2_volatility_filtering(clean_df):
    print("\n--- Task 2: Volatility Filtering ---")

    clean_df['Price_Swing'] = clean_df['Close'] - clean_df['Open']

    print(clean_df[(clean_df['Price_Swing'] > 2.00) | (clean_df['Price_Swing'] < -2.00)])

def task_3_financial_summary(clean_df):
    print("\n--- Task 3: Financial Summary ---")

    print(clean_df['Close'].describe())

    print(clean_df['Volume'].max())

def task_4_algorithmic_metrics(clean_df):
    print("\n--- Task 4: Algorithmic Metrics ---")

    clean_df['Daily_Return'] = clean_df['Close'].pct_change()

    clean_df['2_Day_MA'] = clean_df['Close'].rolling(window=2).mean()

    print(clean_df)

if __name__ == "__main__":
    clean_df = task_1_data_cleaning()
    if clean_df is not None:
        task_2_volatility_filtering(clean_df.copy())
        task_3_financial_summary(clean_df.copy())
        task_4_algorithmic_metrics(clean_df.copy())
