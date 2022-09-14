var exec = require('cordova/exec');

exports.patch = function (arg0, success, error) {
    exec(success, error, 'AndroidSupport', 'patch', [arg0]);
};
