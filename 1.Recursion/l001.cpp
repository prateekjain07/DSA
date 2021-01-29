//g++ l001.cpp -o out && ./out > output.txt
#include <iostream>
using namespace std;
void solve()
{
    printIncDec(3, 10);
}

void printIncDec(int a, int b)
{
    if (a > b)
    {
        cout << endl;
        return;
    }
    cout << a << "-";
    printIncDec(a + 1, b);
    cout << a << "-";
}

int main()
{
    solve();
    return 0;
}