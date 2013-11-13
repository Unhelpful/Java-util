package us.looking_glass.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

/**
 * Copyright 2013 Andrew Mahone
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class Util {
    public static String stringFromStream(InputStream in, Charset cs) throws IOException {
        char[] buf = new char[256];
        StringBuffer result = new StringBuffer();
        Reader cis = new InputStreamReader(in);
        int read = 0;
        while ((read = cis.read(buf, 0, buf.length)) > 0) {
            result.append(buf, 0, read);
        }
        in.close();
        return result.toString();
    }
}
