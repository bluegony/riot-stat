///**
// * Created on 2016. 8. 9..
// */
//public class LSWRC {
//    public int lengthOfLongestSubstring(String s) {
//        int sl = s.length();
//        char[] a = s.toCharArray();
//        Map<char,int> map = new HashMap<char,int>();
//        int longest = 0;
//
//        for(int i=0;i<sl;i++) {
//            Object old =  map.put(a[i],i);
//            if(old != null) {
//                int newGap = (Integer)old - i;
//                (newGap>longest)?longest = newGap:;
//            }
//
//        }
//        return longest;
//    }
//}