package com.ots.alipay;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {

    public static String app_id = "2016092900620817";
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCo2PaWXnrFEShTTHKsqMWwBYGO+6aUey//wM9AHkOrkqRI55WB+eD9SrblnSKUSojOa+3JKosjfHV8EolYFAn3qTZOUU5mJvCsDp+2EzfRcYtnMveNqnMlEzkAGqwCaowh0IB7V7w4UhbZDNUVX0vfMuo8mhcz8F9jE4OXfacNfSLSHVuBPLqiX/9ObfC63dZCnLwTzpZTuVIlSlWUDj2JFgLzoZdISHkM3D8rqHCBPadvvmXBNyd+GuHTDhYL1r6BT5f8zpEG1epXi7hg89zj07m5ZwQTjsah+SFCjn0jVl7WX1Wt4a25RDZZUQ1cJQ0N9IhsrxhWKGdB2nfYA5cNAgMBAAECggEBAIfx1+gNvc85FzyqCpJVeIVWiY+zsdVOscC7XqKDO3zCnp9MRiXWKH4HT+bjc1nuAOQiJduX81ro9DanPgm6aKQMi3rXH8ZDh/cf9xtDsediAhlfAc3/9KFfLr+UufVgD9+R3kBwzUyTW236VP3WMmJMvid48UGSQVql9YddVckXLqp38HPnJ2XrP5o24YAY7LE7IytJat3VbG6AMDOV6ZI+QaNA4cFDQd55bKWBG0XbMYpaLhRgNDgzgfVIXRb1Hbqus2I38BUDoNxRLPcJTDb/bE/uLapjO3iuDbsVRje7uyRt5H4QpLkYWPkZgeTSOo0D5MDA+9zzWYN4HRXw/EECgYEA1fjvOLY9wrSKESJpU8hQkbRFPLgBRA8nJAB9DVQvP1gb0P0kW3pfIDwan0+Sy4WFSr4N5NVGfi1FC+R5BqZnAE2DhqjPihr+j6uX83wyKPGsSqAXoMFdBYo0alt48MHrvtAd7+HRFquLTMpde4X5U8vwjMlzfWEXsTRA496+kpkCgYEAygMJdoXVVdnahbWvV7NXzxaOb2eKQv0p44bAu4jWteHWtgklXbdlJ42zadtWrm32rdglCYLaVNZruu8L5MX2jmBLRFBmTANkcIiGjZ0CgS/byoGeCNX4KpYvKp4j5KLYdhYbAdqsHn4NIFD7/zgpulQyfdoHNvJQOeV/khnI5JUCgYB/IQow7cZAGrg1efaOeuOzziGc8858u2wL7eZetvfjcNwGDYE+gLRoGI3QvZyAI3KQd98VaMobSZZOfI6TruZfva45oa69ZVB91/vOBKpgvUYaiIfBUWGNWxHNZ0+x6+W/VzOSRN7P01hOZaWpMG50pSTk4QQnKDrFZEBxovQjcQKBgQDFmQzjk2um607V5W7bIUEt/slabXQPrKURutjBV8t1469rJi0Xq3FVrESnsfXq7wMQ71DjKcy4mXiNRJBkucM/Zy+YZfB6QAM6FEFROhieFxstzXWw/OMffQIr9L31QdjsQRkvyh78QJ5EUxL7hYtDiGJPOjo+Y+1XhIYxeelItQKBgCp1a6uS5NrWpq1MQeJr4QYHlMtskFd1ROeqIAoKlTPRhDWAj44LOuC8dheWOQ8c4WMi08nudSNNdmH0lxI11eX/7Sm2oV5SVn9udDlSfUOH6bp3z61/cQEwlRX9jpurNdx5SBtfCwqgjiDSRp/ee8z9VcjBqKtyXftBvujTZInq";
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsfN0ng3yNS5OwodDcMz4TpYSj4ct6tqA7R3cpwm4se8YTLQpArcoSxhP9Oy/e0VVzksdQiW/3+MgcaatgoSMyRDLtM4ToChMbwUTOXwFVqr4FbIsUEe0ebjzBoEQMgEFBgvDCaDNpBpSAyQ1ztgI0wN7xvFvQWGoJVBhEUPye2v9Pfh107hKcbMiuVgTQ475G7CtGxpnrDSLpK68he6/82M3yG+433lPwoVxkBgbvNb64bOITTf7zP6XiZhnmqQuoP83xPtKD4gNupBOMN5mUS0epeGC3mFnk6PIY9W6I67CZ72icdWeqcwLw5ZJehiLSVt+bTzha3ie1WWtMxgs7QIDAQAB";
    public static String notify_url = "http://127.0.0.1:8080/ots/alipay/payview/notify_url";
    public static String return_url = "http://localhost:8080/ots/frontsite/vipOrder";
    public static String sign_type = "RSA2";
    public static String charset = "utf-8";
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
    public static String log_path = "E:\\";

    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
