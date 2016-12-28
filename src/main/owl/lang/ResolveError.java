/*
 * Copyright 2016 Igor Demura
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package owl.lang;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.stream.Collectors;

class ResolveError extends Exception {
    // Empty
}

final class ResolveErrorNoMatch extends ResolveError {
    final String name;
    final List<Entity> matches;

    ResolveErrorNoMatch(String name, List<Entity> matches) {
        this.name = name;
        this.matches = matches == null? ImmutableList.of(): ImmutableList.copyOf(matches);
    }

    @Override
    public String getMessage() {
        if (matches.size() == 0) {
            return "function " + name + ": no matches";
        } else {
            return "function " + name + ": match not found:\n" +
                    Util.joinLines(matches.stream()
                            .map(s -> "  " + s)
                            .collect(Collectors.toList()));
        }
    }
}

final class ResolveErrorType extends ResolveError {
    final String name;

    ResolveErrorType(String name) {
        this.name = name;
    }

    @Override
    public String getMessage() {
        return name + " is not a function";
    }
}


