package owl.compiler;

import java.util.List;

// Base class for all abstract types. Abstract type is a (parametric) type programmer defines in the source code or
// primitive types + array type.
abstract class AstAbstractType extends AstNode
        implements Named {
    abstract List<TypeParam> getTypeParams();
}
