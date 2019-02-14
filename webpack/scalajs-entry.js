if (process.env.NODE_ENV === "production") {
    const opt = require("./scalajs-react-components-semantic-ui-opt.js");
    opt.main();
    module.exports = opt;
} else {
    var exports = window;
    exports.require = require("./scalajs-react-components-semantic-ui-fastopt-entrypoint.js").require;
    window.global = window;

    const fastOpt = require("./scalajs-react-components-semantic-ui-fastopt.js");
    fastOpt.main()
    module.exports = fastOpt;

    if (module.hot) {
        module.hot.accept();
    }
}
