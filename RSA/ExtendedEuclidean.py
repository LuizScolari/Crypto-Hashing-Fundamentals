# a > b 
def egcd(a, b):
    # base case 
    # gcd(a,b) = a, a=1, b=0  |  ax + by = gcd(a,b)
    if b == 0:
        return a, 1, 0
    
    gcd, x1, y1 = egcd(b, a%b)

    x = y1
    y = x1 - (a // b) * y1

    return gcd, x, y
