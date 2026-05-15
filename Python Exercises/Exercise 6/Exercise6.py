import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns

"""
INSTRUCTIONS:
Part A: Complete the visualization tasks to analyze a mock financial dataset.
Part B: Write testable logic and Pytest assertions to verify your financial math.
"""

# ==========================================
# PART A: VISUALIZATION
# ==========================================

def get_crypto_data():
    """Helper function to load mock crypto data."""
    return pd.DataFrame({
        "Day": [1, 2, 3, 4, 5, 6, 7],
        "Bitcoin": [40000, 42000, 41000, 45000, 44000, 46000, 48000],
        "Ethereum": [2500, 2600, 2550, 2800, 2750, 2900, 3100]
    })

def task_1_trend_line():
    """
    TASK 1: Matplotlib Line Chart
    1. Load the data using get_crypto_data().
    2. Use plt.plot() to chart Bitcoin prices over the 7 days.
    3. Add a title, x-axis label, and y-axis label.
    4. Call plt.show() to render it.
    """
    print("--- Task 1: Building a Trend Line ---")
    crypto_frame = get_crypto_data()

    day_numbers = crypto_frame["Day"]
    btc_prices = crypto_frame["Bitcoin"]

    plt.figure(figsize=(8, 4))
    plt.plot(day_numbers, btc_prices, color="black", linewidth=2, marker="D")
    plt.title("Bitcoin Closing Prices Across 7 Days")
    plt.xlabel("Day")
    plt.ylabel("Price in USD")
    plt.grid(True, linestyle="--", alpha=0.4)
    plt.show()

def task_2_seaborn_comparison():
    """
    TASK 2: Seaborn Bar Chart
    1. Create a simple DataFrame mapping 3 portfolios to their Total Value.
       (e.g., 'Portfolio A': 10000, 'Portfolio B': 15000, 'Portfolio C': 8000)
    2. Use sns.barplot() to display the comparison.
    3. Call plt.show() to render it.
    """
    print("--- Task 2: Seaborn Comparison ---")

    holdings = pd.DataFrame({
        "Portfolio": ["Growth Fund", "Income Fund", "Starter Fund"],
        "Total Value": [11200, 15850, 9350]
    })

    plt.figure(figsize=(8, 4))
    sns.barplot(data=holdings, x="Portfolio", y="Total Value", palette="Greys")
    plt.title("Portfolio Total Value Comparison")
    plt.xlabel("Portfolio Name")
    plt.ylabel("Total Value (USD)")
    plt.show()

# ==========================================
# PART B: TESTABLE LOGIC
# ==========================================

def calculate_net_gain(selling_price, purchase_price):
    """Returns the amount gained or lost from a trade."""
    return selling_price - purchase_price

def calculate_percent_change(original_value, new_value):
    """Returns percent change from original to new value."""
    if original_value == 0:
        raise ValueError("Original value cannot be zero.")
    return ((new_value - original_value) / original_value) * 100

# ==========================================
# PYTEST TESTS
# ==========================================

def test_calculate_net_gain_profit():
    assert calculate_net_gain(48000, 45000) == 3000

def test_calculate_net_gain_loss():
    assert calculate_net_gain(41000, 42000) == -1000

def test_calculate_percent_change_growth():
    assert calculate_percent_change(40000, 48000) == 20.0

def test_calculate_percent_change_drop():
    assert calculate_percent_change(50000, 45000) == -10.0

def test_calculate_percent_change_zero_error():
    import pytest
    with pytest.raises(ValueError):
        calculate_percent_change(0, 1000)

if __name__ == "__main__":
    # Uncomment to test visuals during development
    # task_1_trend_line()
    # task_2_seaborn_comparison()
    pass
