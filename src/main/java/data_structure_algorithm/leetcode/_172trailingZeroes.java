package data_structure_algorithm.leetcode;

public class _172trailingZeroes {

    public static class Solution1 {

        /**
         数学：https://segmentfault.com/a/1190000038384301?u_atoken=44c8450e-8453-4893-bd6d-5e02474c7308&u_asession=01XvZvZG8xP9cemYBkFmE-aggr1qa3Mi3CSI-YsPi2JzdzCg-rldbHkRTsytPh5n3wBbkTOEgH_0RqODlfUY8VZdsq8AL43dpOnCClYrgFm6o&u_asig=05T7GiKxNhs3y2llzrHSslEtdHyGNfHhCf7gPpKA17RbJJ1VzKLJxhxpeXyQaNqltHmYFOb0Z-214M7eo07sbVu6G45o7GQxjgMk4TQQCsfR0iC1_XC6Wx31PZur9Vgwd5GWIls3qlumsJlfpCL-TeKkJgKCH6751ho_w-5EweQXWyuQQkWhQMb-2U3C2V72kcksmHjM0JOodanL5-M1Qs1duU5djGMoLYCtKRNBaHiVDzVmsSKcMQVzs5E3OYMZ1-U1gZwvIQ1X9ohpXFWkDoTqCRYBO0tmhXZMHcR1segjrY94r_LXIIil3Y3aVPRGAe&u_aref=AZuCnHVT3IgQilEcGXVAdFdtb3U%3D
         阶乘结果巨大，比指数都大，直接计算结果不现实，会溢出
         0-9中只有2*5得到的结果末尾是0，转换为找n!中有多少个2*5即可
         又因为只要是偶数都有因子2，所以2的个数远多于5，进一步转换为找因子5的个数
         5,10,15这种有一个因子5，25，50.75这种有2个因子5，125，250，375这种有3个因子5，...，发现计算规律

         时间复杂度：O(logN)
         空间复杂度：O(1)
         */
         public int trailingZeroes(int n) {
             int count = 0;
             long divisor = 5;
             while (divisor <= n) {
                 count += n / divisor;
                 divisor *= 5;
             }
             return count;
         }

    }



    public static class Solution2 {

        // 更简洁的写法
        public int trailingZeroes(int n) {
            int count = 0;
            for (int d = n; d / 5 > 0; d /= 5) {
                count += d / 5;
            }
            return count;
        }

    }

}
