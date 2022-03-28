grammar DSL;

// init: Statement;
init: couponstatement | fullminusstatement | discountstatement;

couponstatement: 'Coupon' INT condition startclause endclause;

fullminusstatement: 'FullMinus' INT INT condition startclause endclause;

discountstatement: 'Discount' INT condition startclause endclause;

condition: condition '&&' condition
            | condition '||' condition
            | variable '==' INT
            ;

startclause: 'Start' INT '-' INT '-' INT '-' INT ':' INT ':' INT;
endclause: 'End' INT '-' INT '-' INT '-' INT ':' INT ':' INT;
variable: '[商品ID]' | '[品类ID]' | '[品牌ID]';

INT : ('0'..'9')+;
WS: [ \t\r\n]+ -> skip;
NEWLINE:[\r\n]+ ;
