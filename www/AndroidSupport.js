var exec = require('cordova/exec');

exports.start = function (arg0, success, error) {
    exec(success, error, 'AndroidSupport', 'patch', [arg0]);
};
