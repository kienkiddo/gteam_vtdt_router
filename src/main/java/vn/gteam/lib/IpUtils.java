package vn.gteam.lib;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.List;

public class IpUtils {
    private static final List<String> IP_HEADER_CANDIDATES = Arrays.asList(
            "CF-Connecting-IP",
            "True-Client-IP",
            "X-Forwarded-For",
            "X-Real-IP",
            "X-Client-IP",
            "X-Forwarded",
            "Forwarded"
    );

    private IpUtils() {}

    public static String getClientIp(HttpServletRequest request) {
        for (String header : IP_HEADER_CANDIDATES) {
            String value = request.getHeader(header);
            if (value == null || value.isEmpty()) continue;
            if ("Forwarded".equalsIgnoreCase(header)) {
                String ip = parseForwardedFor(value);
                if (isValidIp(ip)) return ip;
                continue;
            }
            if ("X-Forwarded-For".equalsIgnoreCase(header)) {
                for (String part : value.split(",")) {
                    String ip = cleanIp(part.trim());
                    if (isValidIp(ip)) return ip;
                }
                continue;
            }

            String ip = cleanIp(value.trim());
            if (isValidIp(ip)) return ip;
        }
        return cleanIp(request.getRemoteAddr());
    }

    private static String parseForwardedFor(String forwarded) {
        for (String token : forwarded.split(";|,")) {
            String t = token.trim();
            if (t.toLowerCase().startsWith("for=")) {
                String v = t.substring(4).trim();
                if (v.startsWith("\"") && v.endsWith("\"")) v = v.substring(1, v.length()-1);
                if (v.startsWith("[") && v.endsWith("]")) v = v.substring(1, v.length()-1);
                int colon = v.indexOf(':');
                if (colon > -1 && v.indexOf(':', colon + 1) == -1) {
                    v = v.substring(0, colon);
                }
                return v;
            }
        }
        return null;
    }

    private static String cleanIp(String ip) {
        if (ip == null) return null;
        if ("unknown".equalsIgnoreCase(ip)) return null;
        if (ip.startsWith("::ffff:")) ip = ip.substring(7);
        int colon = ip.indexOf(':');
        if (colon > -1 && ip.indexOf(':', colon + 1) == -1 && !ip.contains(".")) {
        } else if (colon > -1 && ip.contains(".")) {
            ip = ip.substring(0, colon);
        }
        return ip;
    }

    private static boolean isValidIp(String ip) {
        if (ip == null || ip.isEmpty()) return false;
        return true;
    }
}
