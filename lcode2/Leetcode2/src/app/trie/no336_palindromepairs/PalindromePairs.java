package app.trie.no336_palindromepairs;

import java.util.ArrayList;
import java.util.List;

class Solution {
    // approach 1: BF O(n^2)
    public List<List<Integer>> palindromePairs1(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (i != j && isPalindrome(words[i], words[j])) {
                    // find a pair
                    List<Integer> r = new ArrayList<>();
                    r.add(i);
                    r.add(j);
                    res.add(r);
                }
            }
        }

        return res;
    }

    public boolean isPalindrome(String str1, String str2) {
        StringBuilder sb = new StringBuilder(str1);
        sb.append(str2);
        char[] combined = sb.toString().toCharArray();

        for (int i = 0, j = combined.length - 1; i < j; i++, j--) {
            if (combined[i] != combined[j]) return false;
        }

        return true;
    }


    // approach 2:
    public List<List<Integer>> palindromePairs(String[] words) {
        return null;
    }
}


public class PalindromePairs {
    public static void main(String[] args) {
        String[] words = new String[] {"abcd","dcba","lls","s","sssll"};
        String[] words1 = new String[] {"bbahiebh","gbiceijg","gagbbbidehagjafdeehg","ggbjdffdjdcddgij","cdijbieb","begh","g","iaibfbaab","hc","icgabfchd","aeh","ii","jjac","ajceaejf","a","cgbfihhijdjbb","ijjeccgdffhbfa","feaiiac","hah","ihgigc","ebigbajjbjaecajha","hefbhiidfbijjbc","haigiggafece","achjfcie","agiec","jfiidfcaaegia","hgiebfhdi","eaic","iiihgachedjbf","ejdgfcabfjcdcbcadgd","gcic","dfbgjedbhcjebdaba","ieheiefafhccc","cdhgigb","ehaeajfieiadabdb","gjdec","chcigeh","hbdgchgffdbbe","ddiehgbjhj","gfdjjbcdbgajcehai","ibebegj","gebfjhgf","acfbiffbafbagehg","cbbbghcbga","iic","ebiie","begijhedgjfefhacibi","fdbdajaagceeijg","ahcjagedejaggabjcj","aijeajgceehba","fggfii","d","bbcb","ee","hghgdcjccdgbdgajdhe","hjii","dbbbdabc","hgb","baecdfj","djaficahjfidhfii","jeidejdgaecb","bddjbcf","ciheacgjd","aj","ffcdfajgifdebgdac","ibceajdhghj","b","iggagfigiheehigdb","fhbiafhfhhcdjj","dicfggchgi","acebghbbiih","ccg","jdjefgdhchji","jihefhcciacdhhe","fajjgidgcg","difchiiij","ghjfjbibeihgech","ahcjadcgehacbejad","dbjahaiibegcahf","bdfifacdg","cghfhgfchiifihgafg","jig","aihgaidf","djbd","jdbbhahdd","gcgijjfficad","fjebfcecgheeeijfe","bijj","dehhfac","beciadeaia","hjbfdcdfahdhc","jaieej","chgjdbebagiihifgjjf","ibdeajabacjahaicgihi","cjadefc","hcjbigf","hhfcidbefbbhfdfcih","igchfjiajfhbcaj","gcae","fha","bfedgghgjddgbadaabeh","chcfdbbjc","jbhdih","cfjfhhdicbdfcccigai","cjgeecbaafi","eidh","ifgjhjedcjbhbdcddihe","did","efg","gijbc","egdjeejhbch","gejhbdgcaifhcca","gdeg","dcfhec","dacbiijaeefjahb","hg","ge","feejfa","hfebdgdbfihdcd","aiaijhicaf","daefhbecahg","jhf","dhbbbeadd","de","ihfad","ijbeeafbbhd","bhj","j","fgjcj","bdhhddfgfgg","dgabaeg","jfdg","ifacjgiejdbbiba","cdgbhidbgehfcgaed","ecceaf","gggaa","gj","dgahejjdaaeegaeb","aceejaaci","fhjjgghaebjiefbgfie","fb","adbdaecgaidjge","afihje","cbhjfg","cifgje","fcbbaceaeeai","ighfgfahba","dgdehfgfdgbgeedafgba","hiddgebiachcdg","cidaedhcgcdebfc","cbidgbb","bfdhdeeadchbdh","bafdiebbdjbighbahha","ihbgdcijegggghj","afeci","ghebgdgee","dhcg","ebeihjha","hdaif","cbaffagaajcjdia","gecegegfidcjhjeaa","idjccf","c","achbaeghjefjabiiddj","idhfbdejabibhj","bdefjcjchhe","biibifajjahibhai","fgifdfefefeadag","dcbhdbjhjcc","ecidfhjhhbjhcdheejf","hfhddhccdbaibijfag","jb","aecgbhbjhichabdbh","ddecj","fbbgffgjgfbf","ahcaefg","dic","ieda","gdcdjfeeiic","hijjebdcfhh","egefffcghh","ifccgdecghijebij","hachjgg","hi","jigffeehhdg","ajjg","eacdhhebfgc","dcibicffjc","ceedggffffghegjhg","cbghbeijg","ihdbhajihhgejabcjcgh","eah","jdicfjbjijcgjeigj","ffdaafidbhd","iaebfiegchbbeg","cjajeb","ec","hbbfgfbed","h","gbghiihejgcdbeca","gfcagccfcfbfhccihg","defcbajgebgd","iiejggededhbhff","hhagbcaddegehg","ebahciadiafhgdd","di","iicidh","dfehjebajbicgg","ejjbecadeahihf","fahgja","defjheded","ebf","ibdgejihadfjhbdj","bgjdhbej","jcjccahdfhbief","gcjhhaifghccghcj","hghehccjfbc","aifdfabjgifciidfegji","feehjg","aghhhjcfaibjff","baifc","efc","jfadgihdeig","acjhdgjijba","iijbdfhaiij","cajahidg","e","iehjeicachcfcjc","gajdjgahhbceej","jfcfge","dcjihhbbdhgee","hgcbb","adfjfdcedjdgfjc","ceahbaegeiifachgcjec","dgf","ibfbj","ijbbdbfdbdaibdaeggag","bbibchbjaahaghd","cchbhdafajbcijab","ib","bgeaffehfhbda","fcd","hbg","fagdbcjacc","aeafiahhdhhahicjeb","baiibgfaijc","ghdifcebbc","aefeaj","fjacciegce","ffgibchc","djbf","dfcjijehcgcifbjgife","ghhcehebbiahdii","ccgag","aajbgbefidifdbjba","bbageca","ficje","id","eed","aeiebeajidhfgdfhh","bdafeagefjj","hjfgaaegdbjdgiaj","fbib","gfihfcgegdhfiiaiiab","ifi","jcbihigbejfgicfegj","ecf","bghfaic","chbccbidfbie","gjbj","hheaehbbjd","dbcaibfdbjb","bbejefhg","bjchjgfggcehihihf","hfbibaccd","hcbjjbd","hefage","gbehcggcfddeieebei","cjead","hj","idej","chhigfcee","hedfge","jedeh","ghajfejehbabajcebiea","dbaceia","hfhg","afaeigfdgbacjcb","ibbbabbj","gagjgb","bgjadadb","hjihbbdbie","eeaffhcbfgaag","f","bhadhdcia","cfbddehdjabdba","icb","biifehdficggigge","bagjggcibf","cacijiec","baejhffaidjeh","acbccafaedg","jijgbbgjgafeii","fbcjfej","ehjfehgaadjgdfcc","bbgahjj","hjiadgdff","djgfhjhdj","dihcecfdjdfi","igfjhbdb","baddaeghhhdiehhfhgdb","cigijacaaaejagie","ja","aeigdgeghdcib","gegjdgc","aafhdjced","cbfgfeafhijfhb","dhfgggjje","dbeciba","iebd","eeagahfcigigbggbc","fbcihbibcjdfcaegc","bgjjadbgg","bj","bbdbehfacbdh","ifihjccddaf","fgie","bibbefjcfddjjch","eidaagifbifjgaejb","ddaihaifjcbffcbdgi","gicgigaigjiaddj","eadj","iidbhdiddbijijjajgjc","gbifebfceaadghbjg","jfffcijfeibagjhc","ag","cbdbiagfgae","gbijdehdjcdfgjb","icfbfhbhhcda","fijabggj","gehae","iafiijdg","eeifgfgeggcddagfdb","ca","iidggjehf","ajceihjbe","afedcea","cd","hib","cfighddjggggi","jahfehigbhaibhei","gdfegda","egcdajajd","je","ahcjgcejifceggcfgdh","ddbdhcdabcaead","iebbeahcebbgggecaf","fjfgajcibiif","ebdccfhfdia","bgdbgaecehjace","abjeej","dcdjaifaffedgjij","gdfadjghachhgbd","ehiccehfidbahb","gf","bcbgb","gdda","jedajeheecddebd","jc","giaegiejbjibhbicffgj","bacdifbbhjc","fjjdffdeiaddi","i","ajbfdgjchhhdcg","diaedfachejdddcfef","jbghcdicfacgb","acgbgedgajbaheecghab","fhbihcgacg","ecbcfej","bjfdeadfehgfedbeff","ifea","jcfjacd","ijgjihdjefaihfaac","hcbag","gbjfjgfjiejfaegbfbja","jej","ibjfgi","hdhddf","ecbhigbifediefibfc","bfddidjgdhfjfffge","igiehedijejbaafdge","fbijbhefebibfic","hdegdgibiabfbgdhjaif","jjfjecbggdifiagg","ecfbbecdedbigajdcci","ihecaifcjeci","ddhgccecdjf","jcibebigigdbhichb","cejhdbihceidcgcebd","bgjijecadd","edbhafiegbaa","dhbj","be","dfbbhbigeaddijefij","ciii","bhaicibhdgcji","gdiddgjdfbfbeifej","hffidjhaac","dbddaccjiehgge","fjdcdhbaajidfed","eie","ebggjhhjhcjbhgdcgga","abcacichghchiabd","ifdhajgadcgjj","jdjhfafhadhfgicifabe","dhiiacdibfcjcb","fadhcgddde","ajbad","bfcbda","jegg","hfd","dgggijbchedfbi","bdafcjiefdhajfaedibf","heeb","iicbdej","fdiafeigjbjhei","dgeihchi","gdbaaefdi","icf","bihaad","ifhihdhdh","hhejegfafbaficf","efad","idghhefidejgh","gddfbedhidjhdhbbiehh","idgggifjjjhgfdjgdgdc","cffjgecjjfdfegcd","ea","gdjahfhfajdg","bdhcdeibdegadbchajh","aheiigc","hgbebjba","efjcihadh","dbfdejd","bggabgfhfee","ggjgdgeaejcd","dehdbea","hfacdifdgccbjheehdbd","bgheiad","bcedfhceiadbjgdege","hffhbhcheef","difhigcdaiifbegd","hfeacfbffdgiiaheaba","dcfaa","iaddfdeddcg","jajjgg","cdhic","iahheh","hihegiegcejdfaidha","jhdfdfhicijfid","dffeajiigaicedahhhc","gggdbahghddchejahd","ddde","chbdebaghjg","haj","cebbdeaiagjd","bjicbcciafjjfd","cgjajdhjhedicj","gchhidhecbbaea","hhffhjgdgjfegbjgaca","bhggfeffhbhgfgeaj","jdcgj","chadeebdeecceff","fbhhcagebgebecca","afagafiafagdifbddhbg","jiaddgcahcbbec","bhdd","adegdaibcj","fidbg","gccahjbahgb","bgig","hdjbdhbifiafejaifiaf","gdfi","cibhiijighcghjed","bbfcbeaeegddeeeccgac","jffddfhgbaajfjijafa","aadgicf","diidieabhdaff","dgagfh","aacgcgchbch","aibeefcg","cgjfghcci","gefaddafjebcid","habihjjcbfegjcii","jf","ajfebbfdg","aaiccb","jhbgd","gjchbbjdecefdeec","hdgabahjgegfddjh","ddgec","fhhhfjjjig","bjeajffcccgcaeb","jjddcciiiib","ida","iiacih","fibjdebbi","dedhdadajfabhgjdh","degdgijebe","cfcifiicjjadgfidfi","iegbhjfdigibiacie","gefgieadfehb","dbabch","adggjgjeihidj","hgagdhdbicejcdhdgbfb","bddfhdhagheb","ajhjhj","bicbd","hhdffihdd","if","geedch","ccdhcfbcd","ddefiiabadiggf","hbaeccbhceahe","bie","gfgecfbg","bihgjbecfjcjag","chhihebc","fbejcbfidjeh","efbbgdaadjccgh","dfhhefjij","bghidhgabjjd","gfgeacceefggjbjc","beeiibhebbedjd","caj","jddjhfgcgjabcacihec","fahbbfbdaeifbdjhehc","bg","agchbgh","gabbjdeiaafcfg","fbfeiibecedbij","jd","cffghfcbgfd","ighcffe","eeggfj","afibeeffab","cfcb","ij","jbfhfidajhgghadgfd","dccfcagbbcfdjebbhjh","aidihaecj","fejgcbbeajbje","fjcigaicbceiedjicfe","iigfbjfhdbhbhe","cedjgcabjhebibf","icbgchbcbdfejaefihi","icei","feajgc","fcadggihecceeidcj","igeeiibigicdaji","egcbgadiaejibief","bhdaegdbjihbhcjbfhhd","dagifgihjaffd","fcggebbj","ijhadf","ageaagijbbec","iijjjb","bffeai","jeghghiheadj","ighj","eacibdehfbffibfcfi","ai","iaehjadheccajcacgaic","gajgddj","diaebg","feddgifdgd","fg","bbgjihgdgedhhaebfbi","cabfjiegfajjcjjhhich","gaaegedbfjcaeciff","faigfa","edcccgcigff","gb","jj","cfehefig","fjdcj","bifejbia","adgjeadd","fbfadg","ejahad","cijefjfegagiaecaidcb","bjhaa","feagccidegjcdcdcci","hfaabajcedha","fbhj","hadidbddiffheb","hdcaehedfcf","beibeeifibafdggdbcii","cgbegjedacbbgheide","ejfdg","iad","ecjdhjbjecjfbdif","jagbajeeh","chfdag","gej","fhfeejcccfgh","dffdfigbca","ahejibgjhbhigbbfbdg","gejadfbffddeahj","aegijjghhcaegdgjdbh","jighifffejcieha","jaebfbbcjdaadiahh","ddhajjdhfhigef","bbidfeedaggibeaf","dae","dbffjgcegdjjjdcf","fgjgdeidajfdcg","dih","fgigb","jhedehebab","fhdahidhjfefebifbif","acddgfgececfa","bbcaacfdbjbedhhg","aidgecficdadg","fdfiahajajeifjehddhi","ihiajedbgcicadia","hggehghjccjfgjbhaeh","dejfcf","diahjigbecejfaedfid","jef","dabhg","bddcgbbhbbgjdedff","cbbhg","bcabdjdaccahf","gbaeghhdcchcje","fheihacfefcdc","aegdfabjdfedce","hhajeaefbeidaie","dfeddhhaieieaa","gcegibbdfiga","jibf","fe","ejicacebiaeebhdei","eddejfeacec","cdcfgdibcahgfg","efifdceajagf","fj","bidcaigiafhaadj","bjehij","gacd","fdaee","dgigfdgh","hcibebcdgj","ajbghiafchee","afiiib","chebfidfdchieccbdgbb","babgfhdcjja","begcjbb","ejcbiiaaaajggaadi","acgjcfefae","eghcefedbddfadaa","feac","jhagihfjjjggj","jaiafeaad","igeajdbicghdid","bjhhh","faeahi","cfacjchhciabeabhbh","chig","fbhaegef","bdaecdibeiajagehgd","gbcceghchj","adiajhbf","jcejfca","ddjafidaeh","diifjjgjfbcaafecffj","gghjcaj","hedgfbjhifdi","aej","fjcbbbjhcjaddhidjce","eagagebcihggechdf","dafgefecjhjee","gbbibi","hggijcjgf","gffjf","hjbccbgjbj","jgecbbchiaahhee","begfjjbcgjegegjicjgh","ecfic","icbgidca","cijaiecii","dhfhga","digdji","dgefjj","bjg","dddc","baeiggifjeihcgcfejh","ceabjdi","beijjfdhhgcjfdegfa","baeag","ffg","edjjjdbgfg","ibacifci","aidcfddcabad","jhjcaejj","ajjdiifdhjgbcaaechd","bbbgc","hdhjfjcjccihehh","iejhadg","dcjigjbihafihhf","ihdhjh","ihfchbba","dhcfbab","cfgdeabbjbdeffd","hchhhjfhfgaaijhgg","ihbffhjggij","eacbchhiaji","feai","jbgjjdabhb","ajdhbeaah","ff","iihbeceaiaefc","bcbjabicjciaa","aei","ghegdghbcbd","acffdgcjdeihifgfhdb","ceib","chdgeiciabagiajdffb","dcgeghdcejcdhdcgja","dgedafc","bbfeffbjigfbiddidg","eji","bjhbbhjdg","gccibagcf","dejhhfhfijh","dhcagecgehcjaajg","fgbeef","icjgdedfbjbbe","jggabdede","bgcaabfiddgiihae","gdfffjdaefajhcbag","jifjddaf","cccgiccgh","ggchbdechfb","bdifgag","bgbbgfdhiiadbdhcd","ahcjgbafdghfgfac","bffeg","jdcgjhegei","bddchfjhifiabcjhaf","jgjdf","echd","fajigaeeg","gdccajjiafgjbdadhbe","gbh","fefbjdiigciadhbegha","fcbaeffehegfgghacheb","ededigeefgjhaeieb","ijjijb","ddeajgiabbggdfe","jfafeibieajchjefi","jeffeabgddhbcichhdba","jfbahffeicdg","caedfdjfhifcdchajaab","ihhafcgfhjjjdajefh","degifehahfahajda","dihgjhhcgcdfjhhchfh","jfeehagdhie","jhac","hfjgggihdd","cfgbciagefcef","dfabhj","eaeaeghjhifj","ccbeddgbehehdgedh","beiadhjcidihgc","biidjaiebcihdifdfdc","jbihciehff","aahgjfej","hcjc","cjdeha","ajcjhjcdce","echffgfeaaefdccicbc","abbdfjdbghbdjai","abjajbgefbbjjeadbjih","jidfeaehajibiddjfa","cghafafabcf","faejihifcadbfgfjeija","bchj","jhdjfhihbcfbiifcjji","egcajeijcjfjffahg","gajigfejiig","jhcei","iaiiicjh","gbjbgghagdejcibicdb","jejbcaigeggadiajjge","bijhgcdcegfhjajgf","caiii","aghgjacbd","gdcigajdfbfaijj","dcbhjjjhceiddiciej","hahbechegbibjacigbaa","chahgadgejaccahgaic","edghijgaicigecacijb","fffbgfjfihebgbdihg","cah","cbchjhigfceca","agigg","iabcd","eijh","aaggfbche","ihgdf","ejgcgfhj","fdiedjfaij","bcfhdejcebdcchih","eeifij","bihjejajeideag","jaib","cedaagfaf","hgcgheejeh","ccgidgdbadch","hjdicfdahfjaagdj","iibh","jhihgdahabd","bjgfh","cbfgeegceecbbedbihe","babeiedh","hddcfahigicefgcjhhec","aigfeidbhabfgdbjde","iadhhacfbhcfdiie","cdeaibgdgf","hcfbd","gbjacihdjbfjdac","ifbdbbefcdghabihdc","jjfajaa","hfbegeigfgjceba","eaheggdagjhjdfchf","gfaffhhacbegcgb","ghibcjgdjeeef","dccfggjjhihababifaai","ajcieiedaj","cebbjdcecgeffc","fiagh","cehi","cggaedihihjfcgjfjbbe","igdic","beafchghibf","bdbggja","haedbifebgicajfiche","djifiihihaje","jafdaefaaghaafb","eajdj","fbdjfehfejiifcfagdih","fa","bchba","ihd","gfifbajbedic","eecbede","jhhaidijeihehefjjdah","ccccdgdechgffjhf","bgegahhciahaehabeghd","ifjeaggahfci","efhgjah","cijcbgbjficdaedbh","ac","abdbedcfihagachdj","adjbfhhbbhheeehfjief","icjcggjigfg","ebeficiajcihdeidfjei","aca","cbehiahfeadfggha"};
        Solution s = new Solution();
        List<List<Integer>> res =  s.palindromePairs1(words1);
        System.out.println(res);
    }
}