package bgdt.hyperWrapper;

import bgdt.hyperRule.HyperRuleException;


public class Manage {

    public static void main(String[] args) {

//        while (true){
//            try{
//                BufferedReader strin =new BufferedReader(new InputStreamReader(System.in));
//                System.out.print("calc > : ");
//                String str = strin.readLine();
//
//                HyperRuleValue res = HyperRuleStringWrapper.parseString(str);
//                System.out.println("result："+ res);
//
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//
//
//        }


        // 多行测试
        try{
            HyperRuleStringWrapper.multiLineParseString();
        }catch (HyperRuleException e){
            System.out.println(e);
        }

    }

}